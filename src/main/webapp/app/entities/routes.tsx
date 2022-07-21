import React from 'react';
import { Switch } from 'react-router-dom';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Promotion from './promotion';
import FormMetaData from './form-meta-data';
import FieldMetaData from './field-meta-data';
import Option from './option';
import FormSubmission from './form-submission';
import FieldSubmission from './field-submission';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default ({ match }) => {
  return (
    <div>
      <Switch>
        {/* prettier-ignore */}
        <ErrorBoundaryRoute path={`${match.url}promotion`} component={Promotion} />
        <ErrorBoundaryRoute path={`${match.url}form-meta-data`} component={FormMetaData} />
        <ErrorBoundaryRoute path={`${match.url}field-meta-data`} component={FieldMetaData} />
        <ErrorBoundaryRoute path={`${match.url}option`} component={Option} />
        <ErrorBoundaryRoute path={`${match.url}form-submission`} component={FormSubmission} />
        <ErrorBoundaryRoute path={`${match.url}field-submission`} component={FieldSubmission} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </Switch>
    </div>
  );
};
