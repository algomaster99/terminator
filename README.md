# Terminator

[![tests](https://github.com/ASSERT-KTH/terminator/actions/workflows/tests.yml/badge.svg)](https://github.com/ASSERT-KTH/terminator/actions/workflows/tests.yml)

A proof-of-concept to illustrate termination of Java virtual machine if a
prohibited method in invoked. The proof-of-concept is located in
[poc branch](https://github.com/ASSERT-KTH/terminator/tree/poc). Checkout the README on that branch for instructions. 

## [Visualization by GitHub Next](https://githubnext.com/projects/repo-visualization/)

![Visualization of the codebase](./diagram.svg)

## Project structure

The project is structured as follows:

1. `classfile-fingerprint` - CLI to generate fingerprints of class files of a
   Java project using maven project or SBOM.
2. `watchdog-agent` - Java agent that is attached to the JVM and verifies the
   fingerprints of loaded classes.

## `classfile-fingerprint`

### CLI

```shell
java -jar classfile-fingerprint-0.8.1-SNAPSHOT.jar
```

#### Required parameters

|     Parameter     |  Type  | Description                                |
|:-----------------:|:------:|--------------------------------------------|
| `-i` or `--input` | `File` | Path to the input SBOM in CycloneDX format |

#### Optional parameters

|         Parameter         |   Type   | Description                                                                                                                                                                                                  |
|:-------------------------:|:--------:|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|   `-a` or `--algorithm`   | `String` | Algorithm used to generate the hash sum. Default: `SHA256`.<br/> All options are [written here](https://docs.oracle.com/en/java/javase/17/docs/specs/security/standard-names.html#messagedigest-algorithms). |
|    `-o` or `--output`     |  `Path`  | Path to the output file. Default: `classfile.sha256.jsonl`                                                                                                                                                   |
| `-e` or `--external-jars` |  `Path`  | Configuration file to specify external jars. Default: `null`.                                                                                                                                                |


### Maven plugin

#### Pom Configuration

```xml
<plugin>
    <groupId>io.github.algomaster99</groupId>
    <artifactId>classfile-fingerprint</artifactId>
    <version>latest version here</version> <!-- use latest version here -->
    <configuration>
        <algorithm>SHA256</algorithm> <!-- optional -->
        <externalJars>path to jar</externalJars> <!-- optional -->
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>generate</goal>
            </goals>
        </execution>
    </executions>
</plugin>

```

Run: `mvn compile`.

It attaches to `compile` phase by default. We recommend to not change the
phase preceding `compile` phase as it may not fingerprint the source files
themselves.

#### Via command line

```bash
mvn compile io.github.algomaster99:classfile-fingerprint:generate
```

**Optional parameters**

|   Parameter    |   Type   | Description                                                                                                                                                                                                  |
|:--------------:|:--------:|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|  `algorithm`   | `String` | Algorithm used to generate the hash sum. Default: `SHA256`.<br/> All options are [written here](https://docs.oracle.com/en/java/javase/17/docs/specs/security/standard-names.html#messagedigest-algorithms). |
| `externalJars` |  `File`  | Configuration file to specify external jars. Default: `null`.                                                                                                                                                |

> `externalJars` is a JSON file with the following structure:
> ```json
> [
>  {
>   "path": "path/to/jar"
>  }
> ]
> ```
> 1. Path to `externalJars` **must** be absolute if the maven project is multimodular.
> 2. The `path` inside the file is relativized to the path of the `externalJars` file itself.
>     However, if the path is absolute, it is not relativized.

Both methods will output a file `classfile.sha256.jsonl` in the `target` directory.

## `watchdog-agent`

Run it as follows:

```bash
java -javaagent:<path/to/agent>=fingerprints=<path/to/fingerprints> -jar <path/to/your/executable/jar>
```

**Required Parameters**

|   Parameter    |  Type  | Description                                                         |
|:--------------:|:------:|---------------------------------------------------------------------|
| `fingerprints` | `File` | Path to the fingerprints file generated by `classfile-fingerprint`. |

**Optional Parameters**

|   Parameter    |   Type    | Description                                                                                      |
|:--------------:|:---------:|--------------------------------------------------------------------------------------------------|
| `skipShutdown` | `boolean` | If `true`, the JVM will not shutdown if a prohibited class is loaded. Default: `false`.          |
|     `sbom`     |  `File`   | Path to an SBOM file. It is used for including the classes of the root project. Default: `null`. |

> `sbom` is a CycloneDX 1.4 JSON file.
