package ServidorAplicacao.strategy.enrolment.degree.strategys;

import ServidorAplicacao.strategy.enrolment.degree.EnrolmentContext;
import ServidorAplicacao.strategy.enrolment.degree.rules.EnrolmentFilterAutomaticEnrolmentRule;
import ServidorAplicacao.strategy.enrolment.degree.rules.EnrolmentFilterCurricularYearPrecedence;
import ServidorAplicacao.strategy.enrolment.degree.rules.EnrolmentFilterLEQTrainingCourseRule;
import ServidorAplicacao.strategy.enrolment.degree.rules.EnrolmentFilterNACandNDRule;
import ServidorAplicacao.strategy.enrolment.degree.rules.EnrolmentFilterPrecedenceNotDoneRule;
import ServidorAplicacao.strategy.enrolment.degree.rules.EnrolmentFilterPrecedenceSpanRule;
import ServidorAplicacao.strategy.enrolment.degree.rules.EnrolmentFilterRestrictedOptionalCoursesRule;
import ServidorAplicacao.strategy.enrolment.degree.rules.EnrolmentFilterRestrictedOptionalDegreeRule;
import ServidorAplicacao.strategy.enrolment.degree.rules.EnrolmentValidateCurricularYearPrecedenceRule;
import ServidorAplicacao.strategy.enrolment.degree.rules.EnrolmentValidateLEQLaboratoryRule;
import ServidorAplicacao.strategy.enrolment.degree.rules.EnrolmentValidateNACandNDRule;
import ServidorAplicacao.strategy.enrolment.degree.rules.IEnrolmentRule;

/**
 * @author dcs-rjao
 *
 * 3/Abr/2003
 */
public class EnrolmentStrategyLEQ extends EnrolmentStrategy implements IEnrolmentStrategy {

	public EnrolmentStrategyLEQ() {
	}

	public EnrolmentContext getAvailableCurricularCourses() {
		
		IEnrolmentRule enrolmentRule = null;

		super.setEnrolmentContext(super.filterBySemester(super.getEnrolmentContext()));

//		this.enrolmentContext = super.filterByExecutionCourses(this.enrolmentContext);

		enrolmentRule = new EnrolmentFilterCurricularYearPrecedence();
		super.setEnrolmentContext(enrolmentRule.apply(super.getEnrolmentContext()));

		enrolmentRule = new EnrolmentFilterAutomaticEnrolmentRule();
		super.setEnrolmentContext(enrolmentRule.apply(super.getEnrolmentContext()));		

		enrolmentRule = new EnrolmentFilterLEQTrainingCourseRule();
		super.setEnrolmentContext(enrolmentRule.apply(super.getEnrolmentContext()));		

		//	NOTE DAVID-RICARDO: Esta regra para ser geral para todos os cursos TEM que ser chamada em penultimo
		enrolmentRule = new EnrolmentFilterPrecedenceSpanRule();
		super.setEnrolmentContext(enrolmentRule.apply(super.getEnrolmentContext()));

		//	NOTE DAVID-RICARDO: Esta regra para ser geral para todos os cursos TEM que ser a ultima a ser chamada
		enrolmentRule = new EnrolmentFilterNACandNDRule();
		super.setEnrolmentContext(enrolmentRule.apply(super.getEnrolmentContext()));

		return super.getEnrolmentContext();
	}

	public EnrolmentContext validateEnrolment() {
		IEnrolmentRule validateRule = null;

		validateRule = new EnrolmentValidateNACandNDRule();
		super.setEnrolmentContext(validateRule.apply(super.getEnrolmentContext()));

		validateRule = new EnrolmentValidateCurricularYearPrecedenceRule();
		super.setEnrolmentContext(validateRule.apply(super.getEnrolmentContext()));

		validateRule = new EnrolmentValidateLEQLaboratoryRule();
		super.setEnrolmentContext(validateRule.apply(super.getEnrolmentContext()));

		return super.getEnrolmentContext();
	}

	public EnrolmentContext getOptionalCurricularCourses() {
		IEnrolmentRule enrolmentRule = null;

		enrolmentRule = new EnrolmentFilterRestrictedOptionalCoursesRule();
		super.setEnrolmentContext(enrolmentRule.apply(super.getEnrolmentContext()));

		enrolmentRule = new EnrolmentFilterPrecedenceNotDoneRule();
		super.setEnrolmentContext(enrolmentRule.apply(super.getEnrolmentContext()));

		return super.getEnrolmentContext();
	}

	public EnrolmentContext getDegreesForOptionalCurricularCourses() {

		IEnrolmentRule enrolmentRule = null;

		enrolmentRule = new EnrolmentFilterRestrictedOptionalDegreeRule();
		super.setEnrolmentContext(enrolmentRule.apply(super.getEnrolmentContext()));

		return super.getEnrolmentContext();
	}
}