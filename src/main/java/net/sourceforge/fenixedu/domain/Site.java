/*
 * Site.java
 * Mar 10, 2003
 */
package net.sourceforge.fenixedu.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.fenixedu.domain.accessControl.EveryoneGroup;
import net.sourceforge.fenixedu.domain.accessControl.InternalPersonGroup;
import net.sourceforge.fenixedu.injectionCode.IGroup;

import org.fenixedu.bennu.core.domain.Bennu;

import pt.utl.ist.fenix.tools.util.i18n.Language;
import pt.utl.ist.fenix.tools.util.i18n.MultiLanguageString;

/**
 * @author Ivo Brandão
 */
public abstract class Site extends Site_Base {

    public Site() {
        super();

        setBennu(Bennu.getInstance());
    }

    public Section createSection(MultiLanguageString sectionName, Section parentContainer, Integer sectionOrder) {
        return new Section(parentContainer, sectionName, sectionOrder);
    }

    public abstract IGroup getOwner();

    public List<Section> getTopLevelSections() {
        return getAssociatedSections();
    }

    public List<Section> getAllAssociatedSections() {
        List<Section> sections = new ArrayList<Section>();
        for (Section section : getTopLevelSections()) {
            sections.add(section);
            sections.addAll(section.getSubSections());
        }
        return sections;
    }

    public SortedSet<Section> getOrderedTopLevelSections() {
        final SortedSet<Section> sections = new TreeSet<Section>(Section.COMPARATOR_BY_ORDER);
        for (final Section section : getAssociatedSections()) {
            sections.add(section);
        }
        return sections;
    }

    public int getNumberOfTopLevelSections() {
        return getAssociatedSections().size();
    }

    public void copySectionsAndItemsFrom(Site siteFrom) {
        for (Section sectionFrom : siteFrom.getAssociatedSections()) {
            Section sectionTo = addAssociatedSections(sectionFrom.getName());
            sectionTo.copyItemsFrom(sectionFrom);
            sectionTo.copySubSectionsAndItemsFrom(sectionFrom);
        }
    }

    /**
     * Obtains a list of all the groups available in the context of this site.
     * 
     * @return
     */
    public List<IGroup> getContextualPermissionGroups() {
        List<IGroup> groups = new ArrayList<IGroup>();

        groups.add(new EveryoneGroup());
        groups.add(new InternalPersonGroup());

        return groups;
    }

    public void setTopLevelSectionsOrder(List<Section> sections) {
        // SECTION_ORDER_ADAPTER.updateOrder(this, sections);
    }

    public ExecutionSemester getExecutionPeriod() {
        return null;
    }

    public static List<Section> getOrderedSections(Collection<Section> sections) {
        List<Section> orderedSections = new ArrayList<Section>(sections);
        Collections.sort(orderedSections, Section.COMPARATOR_BY_ORDER);

        return orderedSections;
    }

    /**
     * If this site has quota policy or not.
     * 
     * @return <code>true</code> if we should not exceed the size in {@link #getQuota()}
     */
    public boolean hasQuota() {
        return false;
    }

    /**
     * The maximum size that can be occupied by files in this site.
     * 
     * @return the maximum combined sizes (in bytes) of all files in this site.
     */
    public long getQuota() {
        return 0;
    }

    /**
     * Computes the current size (in bytes) occupied by all the files in this
     * site.
     * 
     * @return
     */
    public long getUsedQuota() {
        long size = 0;

        for (Section section : getAssociatedSections()) {
            for (FileContent attachment : section.getChildrenFiles()) {
                size += attachment.getSize();
            }
            for (Item item : section.getAssociatedItems()) {
                for (FileContent file : item.getFileSet()) {
                    size += file.getSize();
                }
            }
        }

        return size;
    }

    public boolean isFileClassificationSupported() {
        return false;
    }

    public boolean hasAnyAssociatedSections() {
        return !getAssociatedSections().isEmpty();
    }

    public List<Section> getAssociatedSections() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public Set<Section> getAssociatedSectionsSet() {
        Set<Section> sections = new HashSet<Section>();
        sections.addAll(getAssociatedSections());
        return sections;

    }

    public int getAssociatedSectionsCount() {
        return getAssociatedSections().size();
    }

    public Section addAssociatedSections(MultiLanguageString sectionName) {
        throw new UnsupportedOperationException("");
    }

    public MultiLanguageString getName() {
        return new MultiLanguageString().with(Language.pt, String.valueOf(getExternalId()));
    }

    public void delete() {
        throw new UnsupportedOperationException("");
    }

    public boolean isDeletable() {
        throw new UnsupportedOperationException("");
    }

    public boolean isAvailable() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean isPublic() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public String getReversePath() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static final class SiteMapper {
        public static <T extends Site> T getSite(HttpServletRequest request) {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

}
