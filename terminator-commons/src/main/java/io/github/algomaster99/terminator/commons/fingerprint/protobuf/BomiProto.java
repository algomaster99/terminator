// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Bomi.proto
// Protobuf Java Version: 4.26.1

package io.github.algomaster99.terminator.commons.fingerprint.protobuf;

public final class BomiProto {
    private BomiProto() {}

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                BomiProto.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {}

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_io_github_algomaster99_terminator_commons_fingerprint_protobuf_ClassFile_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internal_static_io_github_algomaster99_terminator_commons_fingerprint_protobuf_ClassFile_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_io_github_algomaster99_terminator_commons_fingerprint_protobuf_ClassFile_Attribute_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internal_static_io_github_algomaster99_terminator_commons_fingerprint_protobuf_ClassFile_Attribute_fieldAccessorTable;
    static final com.google.protobuf.Descriptors.Descriptor
            internal_static_io_github_algomaster99_terminator_commons_fingerprint_protobuf_Bomi_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internal_static_io_github_algomaster99_terminator_commons_fingerprint_protobuf_Bomi_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

    static {
        java.lang.String[] descriptorData = {
            "\n\nBomi.proto\022>io.github.algomaster99.ter" + "minator.commons.fingerprint.protobuf\"\262\001\n"
                    + "\tClassFile\022\021\n\tclassName\030\001 \002(\t\022f\n\tattribu"
                    + "te\030\002 \003(\0132S.io.github.algomaster99.termin"
                    + "ator.commons.fingerprint.protobuf.ClassF"
                    + "ile.Attribute\032*\n\tAttribute\022\014\n\004hash\030\001 \002(\t"
                    + "\022\017\n\007version\030\003 \002(\t\"d\n\004Bomi\022\\\n\tclassFile\030\001"
                    + " \003(\0132I.io.github.algomaster99.terminator"
                    + ".commons.fingerprint.protobuf.ClassFileB"
                    + "\rB\tBomiProtoP\001"
        };
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData, new com.google.protobuf.Descriptors.FileDescriptor[] {});
        internal_static_io_github_algomaster99_terminator_commons_fingerprint_protobuf_ClassFile_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_io_github_algomaster99_terminator_commons_fingerprint_protobuf_ClassFile_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_io_github_algomaster99_terminator_commons_fingerprint_protobuf_ClassFile_descriptor,
                        new java.lang.String[] {
                            "ClassName", "Attribute",
                        });
        internal_static_io_github_algomaster99_terminator_commons_fingerprint_protobuf_ClassFile_Attribute_descriptor =
                internal_static_io_github_algomaster99_terminator_commons_fingerprint_protobuf_ClassFile_descriptor
                        .getNestedTypes()
                        .get(0);
        internal_static_io_github_algomaster99_terminator_commons_fingerprint_protobuf_ClassFile_Attribute_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_io_github_algomaster99_terminator_commons_fingerprint_protobuf_ClassFile_Attribute_descriptor,
                        new java.lang.String[] {
                            "Hash", "Version",
                        });
        internal_static_io_github_algomaster99_terminator_commons_fingerprint_protobuf_Bomi_descriptor =
                getDescriptor().getMessageTypes().get(1);
        internal_static_io_github_algomaster99_terminator_commons_fingerprint_protobuf_Bomi_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_io_github_algomaster99_terminator_commons_fingerprint_protobuf_Bomi_descriptor,
                        new java.lang.String[] {
                            "ClassFile",
                        });
        descriptor.resolveAllFeaturesImmutable();
    }

    // @@protoc_insertion_point(outer_class_scope)
}