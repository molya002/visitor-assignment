# Visitor Pattern for an Underwater Monitoring Station

Software Design Patterns, Assignment 6

The station keeps a mixed list of acoustic recorders and acoustic beacons.
It needs to estimate daily battery consumption, identify scheduled maintenance,
and prepare a recovery checklist. The sample device values and maintenance
intervals are illustrative.

## Build and run

With JDK 17 or later, run these commands from the project root:

```sh
javac --release 17 -Xlint:all -d out src/*.java
java -cp out Main
```

Expected output:

```text
Daily energy budget: 60.00 Wh
Maintenance due:
REC-01 - service recorder storage
BCN-02 - inspect beacon seal
Recovery checklist:
REC-01 - copy recordings before resetting
BCN-01 - disable pulse transmission
BCN-02 - disable pulse transmission
```

## Pattern roles

| Role | Implementation |
| --- | --- |
| Element interface | `StationDevice.accept(DeviceVisitor)` |
| Concrete elements | `AcousticRecorder`, `AcousticBeacon` |
| Visitor interface | `DeviceVisitor`, with `visitRecorder` and `visitBeacon` |
| Concrete visitors | `DailyEnergyVisitor`, `MaintenanceVisitor`, `RecoveryChecklistVisitor` |
| Object structure and client | `Main` traverses a `List<StationDevice>` and calls `accept` for each operation |

The energy visitor multiplies recorder power by recording hours and beacon
energy per pulse by the daily pulse count. The maintenance visitor checks
elapsed days against each device's service interval. The recovery visitor
produces a device-specific shutdown task.

## How double dispatch works

1. With `device` declared as `StationDevice`, the call
   `device.accept(maintenance)` selects `AcousticBeacon.accept` at runtime
   when the object is a beacon.
2. That method calls `visitor.visitBeacon(this)`. The beacon's `accept` method
   determines which visit method to call. The visitor object's runtime class
   determines which implementation runs, in this case
   `MaintenanceVisitor.visitBeacon`.

The same beacon calls `DailyEnergyVisitor.visitBeacon` when passed the
energy visitor. Switching the element to a recorder selects the corresponding
`visitRecorder` method.

## Clean Code principles

1. **Meaningful names.** `AcousticBeacon` names an element and
   `DailyEnergyVisitor` names an operation. Names such as `wattHoursPerPulse`
   carry the units used in calculations.
2. **Polymorphism for dispatch.** Each element selects its visit method in
   `accept`. The traversal works directly with `StationDevice` and needs
   neither casts nor runtime type checks.
3. **Single responsibility.** Each visitor implements one operation across
   all device types. Maintenance policy changes belong in `MaintenanceVisitor`,
   while energy calculation changes belong in `DailyEnergyVisitor`.
4. **Encapsulation.** Device fields are private and final. The visitors read
   the measurements, service ages, and asset IDs they need through accessors.
   Visitors that accumulate lists return immutable snapshots.
5. **Named constants.** The maintenance intervals have descriptive names
   and live beside the rules that use them, so their meaning is clear at
   each comparison.
6. **Open for extension.** `RecoveryChecklistVisitor` demonstrates a third
   operation. Its class implements the existing interface and the client
   passes it to `accept`. Both element classes and the visitor interface
   stay as they were. Adding a new device type would require a new visit
   method and an implementation in each visitor, which suits a stable
   device structure.

Each visitor instance collects results for one traversal in this demo. To run
another report, create a fresh instance.
