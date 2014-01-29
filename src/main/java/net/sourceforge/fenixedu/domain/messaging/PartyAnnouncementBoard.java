package net.sourceforge.fenixedu.domain.messaging;

public abstract class PartyAnnouncementBoard extends PartyAnnouncementBoard_Base {

    public PartyAnnouncementBoard() {
        super();
    }

    @Override
    public String getFullName() {
        return getParty().getName();
    }

    @Override
    public String getQualifiedName() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(this.getName()).append(" - ").append(this.getParty().getName());
        return buffer.toString();
    }

    @Override
    public void delete() {
        setParty(null);
        super.delete();
    }

    @Deprecated
    public boolean hasParty() {
        return getParty() != null;
    }

}
