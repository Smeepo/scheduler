package de.patricklass.scheduler.model;

public enum InvitationStatus {
    ACCEPTED("accepted"), DECLINED("declined"), NOT_ANSWERED("not answered");

    String name;

    InvitationStatus(String name){
        this.name = name;
    }
}
