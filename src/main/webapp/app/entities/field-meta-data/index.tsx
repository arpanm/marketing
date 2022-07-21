import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FieldMetaData from './field-meta-data';
import FieldMetaDataDetail from './field-meta-data-detail';
import FieldMetaDataUpdate from './field-meta-data-update';
import FieldMetaDataDeleteDialog from './field-meta-data-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FieldMetaDataUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FieldMetaDataUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FieldMetaDataDetail} />
      <ErrorBoundaryRoute path={match.url} component={FieldMetaData} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={FieldMetaDataDeleteDialog} />
  </>
);

export default Routes;
