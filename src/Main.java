import java.util.List;
import java.util.Locale;

public final class Main {
    public static void main(String[] args) {
        List<StationDevice> devices = List.of(
                new AcousticRecorder("REC-01", 4.5, 8, 30),
                new AcousticBeacon("BCN-01", 0.02, 600, 45),
                new AcousticBeacon("BCN-02", 0.03, 400, 91)
        );

        DailyEnergyVisitor energy = new DailyEnergyVisitor();
        MaintenanceVisitor maintenance = new MaintenanceVisitor();
        RecoveryChecklistVisitor recovery = new RecoveryChecklistVisitor();

        for (StationDevice device : devices) {
            device.accept(energy);
            device.accept(maintenance);
            device.accept(recovery);
        }

        System.out.printf(Locale.ROOT, "Daily energy budget: %.2f Wh%n", energy.totalWattHours());
        System.out.println("Maintenance due:");
        maintenance.dueTasks().forEach(System.out::println);
        System.out.println("Recovery checklist:");
        recovery.checklist().forEach(System.out::println);
    }
}
