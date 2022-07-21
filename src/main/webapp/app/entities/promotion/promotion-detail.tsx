import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './promotion.reducer';

export const PromotionDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const promotionEntity = useAppSelector(state => state.promotion.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="promotionDetailsHeading">
          <Translate contentKey="marketingApp.promotion.detail.title">Promotion</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{promotionEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="marketingApp.promotion.name">Name</Translate>
            </span>
          </dt>
          <dd>{promotionEntity.name}</dd>
          <dt>
            <span id="urlName">
              <Translate contentKey="marketingApp.promotion.urlName">Url Name</Translate>
            </span>
          </dt>
          <dd>{promotionEntity.urlName}</dd>
          <dt>
            <span id="desktopImageUrl">
              <Translate contentKey="marketingApp.promotion.desktopImageUrl">Desktop Image Url</Translate>
            </span>
          </dt>
          <dd>{promotionEntity.desktopImageUrl}</dd>
          <dt>
            <span id="tabletImageUrl">
              <Translate contentKey="marketingApp.promotion.tabletImageUrl">Tablet Image Url</Translate>
            </span>
          </dt>
          <dd>{promotionEntity.tabletImageUrl}</dd>
          <dt>
            <span id="mobileImageUrl">
              <Translate contentKey="marketingApp.promotion.mobileImageUrl">Mobile Image Url</Translate>
            </span>
          </dt>
          <dd>{promotionEntity.mobileImageUrl}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="marketingApp.promotion.title">Title</Translate>
            </span>
          </dt>
          <dd>{promotionEntity.title}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="marketingApp.promotion.description">Description</Translate>
            </span>
          </dt>
          <dd>{promotionEntity.description}</dd>
          <dt>
            <span id="tnc">
              <Translate contentKey="marketingApp.promotion.tnc">Tnc</Translate>
            </span>
          </dt>
          <dd>{promotionEntity.tnc}</dd>
          <dt>
            <span id="tncLink">
              <Translate contentKey="marketingApp.promotion.tncLink">Tnc Link</Translate>
            </span>
          </dt>
          <dd>{promotionEntity.tncLink}</dd>
          <dt>
            <span id="landingUrl">
              <Translate contentKey="marketingApp.promotion.landingUrl">Landing Url</Translate>
            </span>
          </dt>
          <dd>{promotionEntity.landingUrl}</dd>
          <dt>
            <span id="position">
              <Translate contentKey="marketingApp.promotion.position">Position</Translate>
            </span>
          </dt>
          <dd>{promotionEntity.position}</dd>
          <dt>
            <span id="isEnabled">
              <Translate contentKey="marketingApp.promotion.isEnabled">Is Enabled</Translate>
            </span>
          </dt>
          <dd>{promotionEntity.isEnabled ? 'true' : 'false'}</dd>
          <dt>
            <span id="startDate">
              <Translate contentKey="marketingApp.promotion.startDate">Start Date</Translate>
            </span>
          </dt>
          <dd>
            {promotionEntity.startDate ? <TextFormat value={promotionEntity.startDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="endDate">
              <Translate contentKey="marketingApp.promotion.endDate">End Date</Translate>
            </span>
          </dt>
          <dd>
            {promotionEntity.endDate ? <TextFormat value={promotionEntity.endDate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="marketingApp.promotion.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{promotionEntity.createdBy}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="marketingApp.promotion.createdDate">Created Date</Translate>
            </span>
          </dt>
          <dd>
            {promotionEntity.createdDate ? (
              <TextFormat value={promotionEntity.createdDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="marketingApp.promotion.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{promotionEntity.updatedBy}</dd>
          <dt>
            <span id="updatedDate">
              <Translate contentKey="marketingApp.promotion.updatedDate">Updated Date</Translate>
            </span>
          </dt>
          <dd>
            {promotionEntity.updatedDate ? (
              <TextFormat value={promotionEntity.updatedDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="marketingApp.promotion.formId">Form Id</Translate>
          </dt>
          <dd>{promotionEntity.formId ? promotionEntity.formId.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/promotion" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/promotion/${promotionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PromotionDetail;
