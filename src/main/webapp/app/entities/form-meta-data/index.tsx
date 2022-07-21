import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FormMetaData from './form-meta-data';
import FormMetaDataDetail from './form-meta-data-detail';
import FormMetaDataUpdate from './form-meta-data-update';
import FormMetaDataDeleteDialog from './form-meta-data-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FormMetaDataUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FormMetaDataUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FormMetaDataDetail} />
      <ErrorBoundaryRoute path={match.url} component={FormMetaData} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={FormMetaDataDeleteDialog} />
  </>
);

export default Routes;
