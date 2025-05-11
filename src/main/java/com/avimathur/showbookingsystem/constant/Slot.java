package com.avimathur.showbookingsystem.constant;

public enum Slot {

    Slot0  ("0000-0100"),
    Slot1  ("0100-0200"),
    Slot2  ("0200-0300"),
    Slot3  ("0300-0400"),
    Slot4  ("0400-0500"),
    Slot5  ("0500-0600"),
    Slot6  ("0600-0700"),
    Slot7  ("0700-0800"),
    Slot8  ("0800-0900"),
    Slot9  ("0900-1000"),
    Slot10 ("1000-1100"),
    Slot11 ("1100-1200"),
    Slot12 ("1200-1300"),
    Slot13 ("1300-1400"),
    Slot14 ("1400-1500"),
    Slot15 ("1500-1600"),
    Slot16 ("1600-1700"),
    Slot17 ("1700-1800"),
    Slot18 ("1800-1900"),
    Slot19 ("1900-2000"),
    Slot20 ("2000-2100"),
    Slot21 ("2100-2200"),
    Slot22 ("2200-2300"),
    Slot23 ("2300-0000");

    final String slotDetail;

    Slot(String slotDetail){
        this.slotDetail = slotDetail;
    }

    public static Slot fromSlotDetails(String input){
        for(Slot slot : Slot.values()){
            if(slot.getSlotDetail().equals(input)){
                return slot;
            }
        }
        return Slot0;
    }

    public static Boolean checkSlotString(String input) {
        for(Slot slot : Slot.values()){
            if(slot.getSlotDetail().equals(input)){
                return true;
            }
        }
        return false;
    }

    public String getSlotDetail(){
        return slotDetail;
    }
}
