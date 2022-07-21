import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IFormMetaData } from 'app/shared/model/form-meta-data.model';
import { getEntities as getFormMetaData } from 'app/entities/form-meta-data/form-meta-data.reducer';
import { IPromotion } from 'app/shared/model/promotion.model';
import { getEntity, updateEntity, createEntity, reset } from './promotion.reducer';

export const PromotionUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const formMetaData = useAppSelector(state => state.formMetaData.entities);
  const promotionEntity = useAppSelector(state => state.promotion.entity);
  const loading = useAppSelector(state => state.promotion.loading);
  const updating = useAppSelector(state => state.promotion.updating);
  const updateSuccess = useAppSelector(state => state.promotion.updateSuccess);
  const handleClose = () => {
    props.history.push('/promotion');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getFormMetaData({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.createdDate = convertDateTimeToServer(values.createdDate);
    values.lastModifiedDate = convertDateTimeToServer(values.lastModifiedDate);

    const entity = {
      ...promotionEntity,
      ...values,
      formId: formMetaData.find(it => it.id.toString() === values.formId.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          createdDate: displayDefaultDateTime(),
          lastModifiedDate: displayDefaultDateTime(),
        }
      : {
          ...promotionEntity,
          createdDate: convertDateTimeFromServer(promotionEntity.createdDate),
          lastModifiedDate: convertDateTimeFromServer(promotionEntity.lastModifiedDate),
          formId: promotionEntity?.formId?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="marketingApp.promotion.home.createOrEditLabel" data-cy="PromotionCreateUpdateHeading">
            <Translate contentKey="marketingApp.promotion.home.createOrEditLabel">Create or edit a Promotion</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="promotion-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('marketingApp.promotion.name')}
                id="promotion-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('marketingApp.promotion.urlName')}
                id="promotion-urlName"
                name="urlName"
                data-cy="urlName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('marketingApp.promotion.desktopImageUrl')}
                id="promotion-desktopImageUrl"
                name="desktopImageUrl"
                data-cy="desktopImageUrl"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('marketingApp.promotion.tabletImageUrl')}
                id="promotion-tabletImageUrl"
                name="tabletImageUrl"
                data-cy="tabletImageUrl"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('marketingApp.promotion.mobileImageUrl')}
                id="promotion-mobileImageUrl"
                name="mobileImageUrl"
                data-cy="mobileImageUrl"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('marketingApp.promotion.title')}
                id="promotion-title"
                name="title"
                data-cy="title"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('marketingApp.promotion.description')}
                id="promotion-description"
                name="description"
                data-cy="description"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField label={translate('marketingApp.promotion.tnc')} id="promotion-tnc" name="tnc" data-cy="tnc" type="text" />
              <ValidatedField
                label={translate('marketingApp.promotion.tncLink')}
                id="promotion-tncLink"
                name="tncLink"
                data-cy="tncLink"
                type="text"
              />
              <ValidatedField
                label={translate('marketingApp.promotion.landingUrl')}
                id="promotion-landingUrl"
                name="landingUrl"
                data-cy="landingUrl"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('marketingApp.promotion.position')}
                id="promotion-position"
                name="position"
                data-cy="position"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('marketingApp.promotion.isEnabled')}
                id="promotion-isEnabled"
                name="isEnabled"
                data-cy="isEnabled"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('marketingApp.promotion.startDate')}
                id="promotion-startDate"
                name="startDate"
                data-cy="startDate"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('marketingApp.promotion.endDate')}
                id="promotion-endDate"
                name="endDate"
                data-cy="endDate"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('marketingApp.promotion.createdBy')}
                id="promotion-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('marketingApp.promotion.createdDate')}
                id="promotion-createdDate"
                name="createdDate"
                data-cy="createdDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('marketingApp.promotion.lastModifiedBy')}
                id="promotion-lastModifiedBy"
                name="lastModifiedBy"
                data-cy="lastModifiedBy"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('marketingApp.promotion.lastModifiedDate')}
                id="promotion-lastModifiedDate"
                name="lastModifiedDate"
                data-cy="lastModifiedDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                id="promotion-formId"
                name="formId"
                data-cy="formId"
                label={translate('marketingApp.promotion.formId')}
                type="select"
              >
                <option value="" key="0" />
                {formMetaData
                  ? formMetaData.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/promotion" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default PromotionUpdate;
