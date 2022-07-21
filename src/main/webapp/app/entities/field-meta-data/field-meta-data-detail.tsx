import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './field-meta-data.reducer';

export const FieldMetaDataDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const fieldMetaDataEntity = useAppSelector(state => state.fieldMetaData.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="fieldMetaDataDetailsHeading">
          <Translate contentKey="marketingApp.fieldMetaData.detail.title">FieldMetaData</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{fieldMetaDataEntity.id}</dd>
          <dt>
            <span id="key">
              <Translate contentKey="marketingApp.fieldMetaData.key">Key</Translate>
            </span>
          </dt>
          <dd>{fieldMetaDataEntity.key}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="marketingApp.fieldMetaData.type">Type</Translate>
            </span>
          </dt>
          <dd>{fieldMetaDataEntity.type}</dd>
          <dt>
            <span id="isMandatory">
              <Translate contentKey="marketingApp.fieldMetaData.isMandatory">Is Mandatory</Translate>
            </span>
          </dt>
          <dd>{fieldMetaDataEntity.isMandatory ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="marketingApp.fieldMetaData.form">Form</Translate>
          </dt>
          <dd>{fieldMetaDataEntity.form ? fieldMetaDataEntity.form.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/field-meta-data" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/field-meta-data/${fieldMetaDataEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FieldMetaDataDetail;
