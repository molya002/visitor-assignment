public final class DailyEnergyVisitor implements DeviceVisitor {
    private double totalWattHours;

    @Override
    public void visitRecorder(AcousticRecorder recorder) {
        totalWattHours += recorder.recordingWatts() * recorder.recordingHoursPerDay();
    }

    @Override
    public void visitBeacon(AcousticBeacon beacon) {
        totalWattHours += beacon.wattHoursPerPulse() * beacon.pulsesPerDay();
    }

    public double totalWattHours() {
        return totalWattHours;
    }
}
