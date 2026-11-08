import java.util.ArrayList;
import java.util.List;

public final class RecoveryChecklistVisitor implements DeviceVisitor {
    private final List<String> checklist = new ArrayList<>();

    @Override
    public void visitRecorder(AcousticRecorder recorder) {
        checklist.add(recorder.assetId() + " - copy recordings before resetting");
    }

    @Override
    public void visitBeacon(AcousticBeacon beacon) {
        checklist.add(beacon.assetId() + " - disable pulse transmission");
    }

    public List<String> checklist() {
        return List.copyOf(checklist);
    }
}
