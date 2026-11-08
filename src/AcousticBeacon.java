public final class AcousticBeacon implements StationDevice {
    private final String assetId;
    private final double wattHoursPerPulse;
    private final int pulsesPerDay;
    private final int daysSinceSealInspection;

    public AcousticBeacon(String assetId, double wattHoursPerPulse,
                          int pulsesPerDay, int daysSinceSealInspection) {
        this.assetId = assetId;
        this.wattHoursPerPulse = wattHoursPerPulse;
        this.pulsesPerDay = pulsesPerDay;
        this.daysSinceSealInspection = daysSinceSealInspection;
    }

    @Override
    public void accept(DeviceVisitor visitor) {
        visitor.visitBeacon(this);
    }

    public String assetId() {
        return assetId;
    }

    public double wattHoursPerPulse() {
        return wattHoursPerPulse;
    }

    public int pulsesPerDay() {
        return pulsesPerDay;
    }

    public int daysSinceSealInspection() {
        return daysSinceSealInspection;
    }
}
