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
package net.sourceforge.fenixedu.domain.util.email;

import net.sourceforge.fenixedu.domain.Person;

public class PersonReplyTo extends PersonReplyTo_Base {

    public PersonReplyTo(Person person) {
        super();
        setPerson(person);
        // System.out.println("Person has new ReplyTo : " + person.getName());
    }

    @Override
    public String getReplyToAddress(final Person person) {
        return getPerson().getEmail();
    }

    @Override
    public String getReplyToAddress() {
        return getPerson().getEmail();
    }

    @Override
    public void safeDelete() {
        if (getPerson() == null) {
            super.safeDelete();
        } else {
            for (final Message message : getMessagesSet()) {
                removeMessages(message);
            }
        }
    }

}
