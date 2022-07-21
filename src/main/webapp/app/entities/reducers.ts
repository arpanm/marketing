import promotion from 'app/entities/promotion/promotion.reducer';
import formMetaData from 'app/entities/form-meta-data/form-meta-data.reducer';
import fieldMetaData from 'app/entities/field-meta-data/field-meta-data.reducer';
import option from 'app/entities/option/option.reducer';
import formSubmission from 'app/entities/form-submission/form-submission.reducer';
import fieldSubmission from 'app/entities/field-submission/field-submission.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  promotion,
  formMetaData,
  fieldMetaData,
  option,
  formSubmission,
  fieldSubmission,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
