package io.github.algomaster99.terminator.index;

import static io.github.algomaster99.terminator.commons.jar.JarScanner.goInsideJarAndUpdateFingerprints;

import io.github.algomaster99.terminator.commons.cyclonedx.Bom15Schema;
import io.github.algomaster99.terminator.commons.cyclonedx.Component__1;
import io.github.algomaster99.terminator.commons.cyclonedx.CycloneDX;
import io.github.algomaster99.terminator.commons.cyclonedx.Metadata__1;
import io.github.algomaster99.terminator.commons.fingerprint.provenance.Jdk;
import io.github.algomaster99.terminator.commons.fingerprint.provenance.Provenance;
import io.github.algomaster99.terminator.commons.jar.JarDownloader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

@CommandLine.Command(
        name = "supply-chain",
        mixinStandardHelpOptions = true,
        description = "Create an index of the classfiles from SBOM")
public class SupplyChainIndexer extends BaseIndexer implements Callable<Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Jdk.class);

    @CommandLine.Option(
            names = {"-s", "--sbom"},
            required = true,
            description = "A valid CyclondeDX 1.4 SBOM")
    private Path sbomPath;

    @Override
    Map<String, List<Provenance>> createOrMergeProvenances(Map<String, List<Provenance>> referenceProvenance) {
        Bom15Schema sbom = null;
        try {
            sbom = CycloneDX.getPojo_1_5(Files.readString(sbomPath));
        } catch (IOException e) {
            LOGGER.error("SBOM could not be parsed.");
            throw new RuntimeException(e);
        }
        if (sbom.getMetadata() != null) {
            LOGGER.debug("Processing root component");
            processRootComponent(sbom, referenceProvenance);
        }
        LOGGER.debug("Processing all components");
        processAllComponents(sbom, referenceProvenance);
        return referenceProvenance;
    }

    private void processRootComponent(Bom15Schema sbom, Map<String, List<Provenance>> referenceProvenance) {
        Metadata__1 metadata = sbom.getMetadata();
        if (metadata == null) {
            LOGGER.warn("Metadata is not present.");
            return;
        }
        Component__1 rootComponent = metadata.getComponent();
        if (rootComponent == null) {
            LOGGER.warn("Root component is not present.");
            return;
        }
        File jarFile;
        try {
            jarFile = JarDownloader.getJarFile(
                    rootComponent.getGroup(), rootComponent.getName(), rootComponent.getVersion());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        goInsideJarAndUpdateFingerprints(
                jarFile,
                referenceProvenance,
                algorithm,
                rootComponent.getGroup(),
                rootComponent.getName(),
                rootComponent.getVersion());
    }

    private void processAllComponents(Bom15Schema sbom, Map<String, List<Provenance>> referenceProvenance) {
        for (Component__1 component : sbom.getComponents()) {
            try {
                File jarFile =
                        JarDownloader.getJarFile(component.getGroup(), component.getName(), component.getVersion());
                if (jarFile == null) {
                    LOGGER.warn(
                            "Could not find jar for {}:{}:{}",
                            component.getGroup(),
                            component.getName(),
                            component.getVersion());
                    continue;
                }
                goInsideJarAndUpdateFingerprints(
                        jarFile,
                        referenceProvenance,
                        algorithm,
                        component.getGroup(),
                        component.getName(),
                        component.getVersion());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}