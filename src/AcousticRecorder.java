public final class AcousticRecorder implements StationDevice {
    private final String assetId;
    private final double recordingWatts;
    private final int recordingHoursPerDay;
    private final int daysSinceStorageService;

    public AcousticRecorder(String assetId, double recordingWatts,
                            int recordingHoursPerDay, int daysSinceStorageService) {
        this.assetId = assetId;
        this.recordingWatts = recordingWatts;
        this.recordingHoursPerDay = recordingHoursPerDay;
        this.daysSinceStorageService = daysSinceStorageService;
    }

    @Override
    public void accept(DeviceVisitor visitor) {
        visitor.visitRecorder(this);
    }

    public String assetId() {
        return assetId;
    }

    public double recordingWatts() {
        return recordingWatts;
    }

    public int recordingHoursPerDay() {
        return recordingHoursPerDay;
    }

    public int daysSinceStorageService() {
        return daysSinceStorageService;
    }
}
