import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './form-meta-data.reducer';

export const FormMetaDataDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const formMetaDataEntity = useAppSelector(state => state.formMetaData.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="formMetaDataDetailsHeading">
          <Translate contentKey="marketingApp.formMetaData.detail.title">FormMetaData</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{formMetaDataEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="marketingApp.formMetaData.name">Name</Translate>
            </span>
          </dt>
          <dd>{formMetaDataEntity.name}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="marketingApp.formMetaData.title">Title</Translate>
            </span>
          </dt>
          <dd>{formMetaDataEntity.title}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="marketingApp.formMetaData.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{formMetaDataEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="multiSubmissionAllowed">
              <Translate contentKey="marketingApp.formMetaData.multiSubmissionAllowed">Multi Submission Allowed</Translate>
            </span>
          </dt>
          <dd>{formMetaDataEntity.multiSubmissionAllowed ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/form-meta-data" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/form-meta-data/${formMetaDataEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FormMetaDataDetail;
