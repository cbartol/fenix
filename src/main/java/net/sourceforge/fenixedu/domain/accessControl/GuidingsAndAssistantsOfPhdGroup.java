/**
 * Copyright © 2002 Instituto Superior Técnico
 *
 * This file is part of FenixEdu Core.
 *
 * FenixEdu Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu Core.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.sourceforge.fenixedu.domain.accessControl;

import java.util.HashSet;
import java.util.Set;

import net.sourceforge.fenixedu.domain.phd.InternalPhdParticipant;
import net.sourceforge.fenixedu.domain.phd.PhdIndividualProgramProcess;
import net.sourceforge.fenixedu.domain.phd.PhdParticipant;

import org.fenixedu.bennu.core.annotation.GroupArgument;
import org.fenixedu.bennu.core.annotation.GroupOperator;
import org.fenixedu.bennu.core.domain.User;
import org.fenixedu.bennu.core.domain.groups.PersistentGroup;
import org.joda.time.DateTime;

import com.google.common.base.Objects;

@GroupOperator("guidingsAndAssistants")
public class GuidingsAndAssistantsOfPhdGroup extends FenixGroup {
    private static final long serialVersionUID = -4915895868986948375L;

    @GroupArgument
    private PhdIndividualProgramProcess process;

    private GuidingsAndAssistantsOfPhdGroup() {
        super();
    }

    private GuidingsAndAssistantsOfPhdGroup(PhdIndividualProgramProcess process) {
        this();
        this.process = process;
    }

    public static GuidingsAndAssistantsOfPhdGroup get(PhdIndividualProgramProcess process) {
        return new GuidingsAndAssistantsOfPhdGroup(process);
    }

    @Override
    public String[] getPresentationNameKeyArgs() {
        return new String[] { process.getProcessNumber() };
    }

    @Override
    public Set<User> getMembers() {
        Set<User> users = new HashSet<>();

        for (PhdParticipant participant : process.getGuidingsAndAssistantGuidings()) {
            if (participant.isInternal()) {
                User user = ((InternalPhdParticipant) participant).getPerson().getUser();
                if (user != null) {
                    users.add(user);
                }
            }
        }
        return users;
    }

    @Override
    public Set<User> getMembers(DateTime when) {
        return getMembers();
    }

    @Override
    public boolean isMember(User user) {
        if (user == null) {
            return false;
        }
        for (InternalPhdParticipant participant : user.getPerson().getInternalParticipantsSet()) {
            if (participant.getIndividualProcess().equals(process)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isMember(User user, DateTime when) {
        return isMember(user);
    }

    @Override
    public PersistentGroup toPersistentGroup() {
        return PersistentGuidingsAndAssistantsOfPhdGroup.getInstance(process);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof GuidingsAndAssistantsOfPhdGroup) {
            return Objects.equal(process, ((GuidingsAndAssistantsOfPhdGroup) object).process);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(process);
    }
}
