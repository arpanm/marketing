import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FieldSubmission from './field-submission';
import FieldSubmissionDetail from './field-submission-detail';
import FieldSubmissionUpdate from './field-submission-update';
import FieldSubmissionDeleteDialog from './field-submission-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FieldSubmissionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FieldSubmissionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FieldSubmissionDetail} />
      <ErrorBoundaryRoute path={match.url} component={FieldSubmission} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={FieldSubmissionDeleteDialog} />
  </>
);

export default Routes;
