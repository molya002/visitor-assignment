public interface DeviceVisitor {
    void visitRecorder(AcousticRecorder recorder);
    void visitBeacon(AcousticBeacon beacon);
}
