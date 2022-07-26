import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './option.reducer';

export const OptionDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const optionEntity = useAppSelector(state => state.option.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="optionDetailsHeading">
          <Translate contentKey="marketingApp.option.detail.title">Option</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{optionEntity.id}</dd>
          <dt>
            <span id="valueStr">
              <Translate contentKey="marketingApp.option.valueStr">Value Str</Translate>
            </span>
          </dt>
          <dd>{optionEntity.valueStr}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="marketingApp.option.title">Title</Translate>
            </span>
          </dt>
          <dd>{optionEntity.title}</dd>
          <dt>
            <span id="isDefault">
              <Translate contentKey="marketingApp.option.isDefault">Is Default</Translate>
            </span>
          </dt>
          <dd>{optionEntity.isDefault ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="marketingApp.option.field">Field</Translate>
          </dt>
          <dd>{optionEntity.field ? optionEntity.field.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/option" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/option/${optionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default OptionDetail;
