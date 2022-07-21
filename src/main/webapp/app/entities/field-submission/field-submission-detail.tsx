import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './field-submission.reducer';

export const FieldSubmissionDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const fieldSubmissionEntity = useAppSelector(state => state.fieldSubmission.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="fieldSubmissionDetailsHeading">
          <Translate contentKey="marketingApp.fieldSubmission.detail.title">FieldSubmission</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{fieldSubmissionEntity.id}</dd>
          <dt>
            <span id="valueStr">
              <Translate contentKey="marketingApp.fieldSubmission.valueStr">Value Str</Translate>
            </span>
          </dt>
          <dd>{fieldSubmissionEntity.valueStr}</dd>
          <dt>
            <Translate contentKey="marketingApp.fieldSubmission.fieldKey">Field Key</Translate>
          </dt>
          <dd>{fieldSubmissionEntity.fieldKey ? fieldSubmissionEntity.fieldKey.id : ''}</dd>
          <dt>
            <Translate contentKey="marketingApp.fieldSubmission.formSub">Form Sub</Translate>
          </dt>
          <dd>{fieldSubmissionEntity.formSub ? fieldSubmissionEntity.formSub.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/field-submission" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/field-submission/${fieldSubmissionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FieldSubmissionDetail;
