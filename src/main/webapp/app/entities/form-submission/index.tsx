import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FormSubmission from './form-submission';
import FormSubmissionDetail from './form-submission-detail';
import FormSubmissionUpdate from './form-submission-update';
import FormSubmissionDeleteDialog from './form-submission-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FormSubmissionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FormSubmissionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FormSubmissionDetail} />
      <ErrorBoundaryRoute path={match.url} component={FormSubmission} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={FormSubmissionDeleteDialog} />
  </>
);

export default Routes;
