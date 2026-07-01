# Java 21 Migration Summary

## Status: BUILD SUCCESS ✅

## Changes Made

OpenRewrite's Java 21 migration produced broken placeholder code (`__P__.<Type>/*__pN__*/p()` and `yield __P__./*__p0__*/nullp()`) in several files where it attempted to convert `if/instanceof` chains and traditional switch statements into modern switch patterns/expressions. All broken placeholders have been replaced with correct Java code.

### Fixed Files

1. **`InstructionPrinter.java`** (`decompilers/bytecode/`)
   - OpenRewrite incorrectly converted a 16-branch `if/instanceof` chain in `printInstruction()` to a broken switch pattern matching block.
   - Restored the original `if (ain instanceof XNode)` chain calling the appropriate `print*` methods (`printVarInsnNode`, `printIntInsnNode`, etc.).

2. **`AllatoriStringDecrypter.java`** (`plugin/preinstalled/`)
   - OpenRewrite incorrectly converted a 3-branch `if/instanceof` loop body in `scanMethodNode()` to a broken switch block.
   - Restored the original logic: tracking `LdcInsnNode` strings on the stack, handling `MethodInsnNode` invokestatic calls for Allatori decryption, and handling `InvokeDynamicInsnNode`.

3. **`BytecodeViewPanelUpdater.java`** (`gui/util/`)
   - Two switch expressions had `yield __P__./*__p0__*/nullp()` as their default cases.
   - Replaced with `yield null` (the correct behavior when panel index is outside 0–2).

4. **`ClassViewer.java`** (`gui/resourceviewer/viewer/`)
   - One switch expression had `yield __P__./*__p0__*/nullp()` as its default case.
   - Replaced with `yield null`.

## Verification

- `mvn compile`: **SUCCESS** (0 errors)
- `mvn test`: **BUILD SUCCESS**

## Next Steps

- None identified. The build is clean with all tests passing.
