import java.util.Arrays;
import java.util.UUID;


public class ParseCommands {
    /*public static void test() {
        HardDrive hd = new HardDrive("test","100g");
        HardDrive hd1 = new HardDrive("another","100g");
        HardDrive hd2 = new HardDrive("epic","100g");
        HardDrive hd3 = new HardDrive("tpooo","100g");
        LocalHDStorage.addDrive(hd);
        LocalHDStorage.addDrive(hd1);
        LocalHDStorage.addDrive(hd2);
        LocalHDStorage.addDrive(hd3);
        LocalHDStorage.displayDrives();
        PhysicalVolume pv1 = new PhysicalVolume("pv1","uuid",hd,hd.getSizeAsInt());
        PhysicalVolume pv2 = new PhysicalVolume("pv2","uuid",hd1,hd1.getSizeAsInt());
        PhysicalVolume pv3 = new PhysicalVolume("pv3","uuid",hd2,hd2.getSizeAsInt());
        PhysicalVolume pv4 = new PhysicalVolume("pv4","uuid",hd3,hd3.getSizeAsInt());
        LocalHDStorage.addVolumes(pv1);
        LocalHDStorage.addVolumes(pv2);
        LocalHDStorage.addVolumes(pv3);
        LocalHDStorage.addVolumes(pv4);
        LocalHDStorage.displayVolumes(1);
        VolumeGroup vg1 = new VolumeGroup("vg1","uuid",pv1,pv1.getSize());
        vg1.addPVList(pv1);
        VolumeGroup vg2 = new VolumeGroup("vg2","uuid",pv2,pv2.getSize());
        vg2.addPVList(pv2);
        VolumeGroup vg3 = new VolumeGroup("vg3","uuid",pv3,pv3.getSize());
        vg3.addPVList(pv3);
        LocalHDStorage.addVolumes(vg1);
        LocalHDStorage.addVolumes(vg2);
        LocalHDStorage.addVolumes(vg3);
        System.out.println("After creating volume groups");
        LocalHDStorage.displayVolumes(1);
        LocalHDStorage.displayVolumes(2);
        vg1.addPVList(pv4);
        System.out.println("Volume group 1 has another pv");
        LocalHDStorage.displayVolumes(2);
        LogicalVolume lv1 = new LogicalVolume("lv1","uuid",vg1,50);
        LogicalVolume lv2 = new LogicalVolume("lv2","uuid",vg1,5102010);
        LogicalVolume lv3 = new LogicalVolume("lv1","uuid",vg1,5);
        LocalHDStorage.addVolumes(lv1);
        LocalHDStorage.addVolumes(lv2);
        LocalHDStorage.addVolumes(lv3);
        vg1.addLVList(lv1);
        vg2.addLVList(lv2);
        vg3.addLVList(lv3);
        LocalHDStorage.displayVolumes(3);
    }*/
    public static void parseMessage(String msg) throws IndexOutOfBoundsException {
        try {
            if (msg.contains("install-drive")) {
                String hdName = msg.split(" ")[1];
                String hdSize = msg.split(" ")[2];
                HardDrive drive = new HardDrive(hdName, hdSize);
                if (LocalHDStorage.hasHD(hdName) != null) {
                    // drive is not present in local storage
                    System.out.println("Error! Disk already exists!");
                } else {
                    LocalHDStorage.addDrive(drive);
                    System.out.println("Drive successfully created!");
                }
            }
            else if (msg.contains("list-drives")) {
                LocalHDStorage.displayDrives();
            }
            else if (msg.contains("pvcreate")) {
                String pvName = msg.split(" ")[1];
                String driveName = msg.split(" ")[2];
                if (LocalHDStorage.hasHD(driveName) == null || LocalHDStorage.hasHD(driveName).isOccupied()) {
                    // drive is not present in local storage
                    System.out.println("Error! Disk does not exist or is occupied");
                } else {
                    // check that physical drive has not been created before
                    if (LocalHDStorage.hasObject(pvName) instanceof PhysicalVolume) {
                        System.out.println("Error! Physical Volume has already been created!");
                    } else {
                        //   drive is not occupied so set it to occupied now;
                        HardDrive hd = LocalHDStorage.hasHD(driveName);
                        hd.setOccupied();
                        // create physical volume below
                        PhysicalVolume newPV = new PhysicalVolume(pvName, UUID.randomUUID().toString(), hd, hd.getSizeAsInt());
                        LocalHDStorage.addVolumes((Volumes) newPV);
                        System.out.println("Physical Volume has been created!");
                    }

                }
            }
            else if (msg.contains("pvlist")) {
                LocalHDStorage.displayVolumes(1);
            }
            else if (msg.contains("vgcreate")) {
                String vgName = msg.split(" ")[1];
                String pvName = msg.split(" ")[2];
                if (LocalHDStorage.hasObject(pvName) instanceof PhysicalVolume) {
                    PhysicalVolume foundPV = (PhysicalVolume) LocalHDStorage.hasObject(pvName);
                    if (foundPV.isOccupied()) {
                        // drive is not present in local storage
                        System.out.println("Error! Physical Volume does not exist or is occupied");
                    } else {
                        // vg free to be used for pv'
                        // create volume group below
                        VolumeGroup vg = new VolumeGroup(vgName, UUID.randomUUID().toString(), foundPV, foundPV.getSize());
                        foundPV.setOccupied(vg);
                        vg.addPVList(foundPV); // add the physical volume to the volume group
                        // add the vg to
                        LocalHDStorage.addVolumes(vg);
                        System.out.println("Successfully created Volume Group [" + vg.getName() + "]");
                    }
                } else
                    System.out.println("Physical Volume does not exist!");


            }
            else if (msg.contains("vgextend")) {
                String vgName = msg.split(" ")[1];
                String pvName = msg.split(" ")[2];
                if (LocalHDStorage.hasObject(pvName) instanceof PhysicalVolume) {
                    PhysicalVolume foundPV = (PhysicalVolume) LocalHDStorage.hasObject(pvName);
                    if (foundPV.isOccupied()) {
                        // drive is not present in local storage
                        System.out.println("Error! Physical Volume does not exist or is occupied");
                    } else {
                        // vg free to be extended
                        // add physical volume group if volume group exists
                        if (LocalHDStorage.hasObject(vgName) instanceof VolumeGroup) {
                            // add physical drive to volume group
                            VolumeGroup vg = (VolumeGroup) LocalHDStorage.hasObject(vgName);
                            vg.addPVList(foundPV);
                            foundPV.setOccupied(vg); // set the physical volume as occupied under volume group
                            System.out.println("Successfully extended the volume group!");
                        }
                    }
                }
            }

            else if (msg.contains("vglist")) {
                LocalHDStorage.displayVolumes(2);
            }
            else if (msg.contains("lvcreate")) {
                String lvName = msg.split(" ")[1];
                String lvSize = msg.split(" ")[2];
                String vgName = msg.split(" ")[3];
                if (vgName != null && LocalHDStorage.hasObject(vgName) instanceof VolumeGroup) {
                    VolumeGroup foundVg = (VolumeGroup) LocalHDStorage.hasObject(vgName);
                    if (foundVg.getAvailableSpace() < Integer.parseInt(lvSize.substring(0, lvSize.length() - 1))) {
                        // drive is not present in local storage
                        System.out.println("Error! Volume Group " + vgName + " does not have enough space!");
                    } else {
                        // volume group has enough space to add an lv

                        // but first check that lv does not already exist
                        if (LocalHDStorage.hasObject(lvName) instanceof LogicalVolume) {
                            // an object of the same name exists
                            if (((LogicalVolume) LocalHDStorage.hasObject(lvName)).getSuperVG() == foundVg) {
                                // volume groups are the same which means that there is a logical volume of the same name in the volume group
                                System.out.println("Error! A Logical Volume of the similar name is already in use!");
                            }
                            System.out.println("Logical Volume name is already taken!");
                        } else {
                            // good to add the logical volume to the volume group
                            LogicalVolume lv = new LogicalVolume(lvName, UUID.randomUUID().toString(), foundVg, Integer.parseInt(lvSize.substring(0, lvSize.length() - 1)));
                            foundVg.addLVList(lv);
                            // add the vg to
                            LocalHDStorage.addVolumes(lv);
                            System.out.println("Successfully created Logical Volume [" + lv.getName() + "]");
                        }
                    }
                } else
                    System.out.println("Volume Group does not exist!");


            }
            else if (msg.contains("lvlist")) {
                LocalHDStorage.displayVolumes(3);
            }
            else
            System.out.println("Not a command!");
        }
        catch (Exception IndexOutOfBoundsException) {
            System.out.println("Not the exact data that I am looking for...");
        }
    }

}
