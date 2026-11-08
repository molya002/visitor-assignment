import java.util.ArrayList;
import java.util.List;

public final class MaintenanceVisitor implements DeviceVisitor {
    private static final int STORAGE_SERVICE_INTERVAL_DAYS = 30;
    private static final int SEAL_INSPECTION_INTERVAL_DAYS = 90;
    private final List<String> dueTasks = new ArrayList<>();

    @Override
    public void visitRecorder(AcousticRecorder recorder) {
        if (recorder.daysSinceStorageService() >= STORAGE_SERVICE_INTERVAL_DAYS) {
            dueTasks.add(recorder.assetId() + " - service recorder storage");
        }
    }

    @Override
    public void visitBeacon(AcousticBeacon beacon) {
        if (beacon.daysSinceSealInspection() >= SEAL_INSPECTION_INTERVAL_DAYS) {
            dueTasks.add(beacon.assetId() + " - inspect beacon seal");
        }
    }

    public List<String> dueTasks() {
        return List.copyOf(dueTasks);
    }
}
