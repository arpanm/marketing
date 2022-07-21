import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IPromotion } from 'app/shared/model/promotion.model';
import { getEntities as getPromotions } from 'app/entities/promotion/promotion.reducer';
import { IFormMetaData } from 'app/shared/model/form-meta-data.model';
import { getEntity, updateEntity, createEntity, reset } from './form-meta-data.reducer';

export const FormMetaDataUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const promotions = useAppSelector(state => state.promotion.entities);
  const formMetaDataEntity = useAppSelector(state => state.formMetaData.entity);
  const loading = useAppSelector(state => state.formMetaData.loading);
  const updating = useAppSelector(state => state.formMetaData.updating);
  const updateSuccess = useAppSelector(state => state.formMetaData.updateSuccess);
  const handleClose = () => {
    props.history.push('/form-meta-data');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getPromotions({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...formMetaDataEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...formMetaDataEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="marketingApp.formMetaData.home.createOrEditLabel" data-cy="FormMetaDataCreateUpdateHeading">
            <Translate contentKey="marketingApp.formMetaData.home.createOrEditLabel">Create or edit a FormMetaData</Translate>
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
                  id="form-meta-data-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('marketingApp.formMetaData.name')}
                id="form-meta-data-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('marketingApp.formMetaData.title')}
                id="form-meta-data-title"
                name="title"
                data-cy="title"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('marketingApp.formMetaData.isActive')}
                id="form-meta-data-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('marketingApp.formMetaData.multiSubmissionAllowed')}
                id="form-meta-data-multiSubmissionAllowed"
                name="multiSubmissionAllowed"
                data-cy="multiSubmissionAllowed"
                check
                type="checkbox"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/form-meta-data" replace color="info">
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

export default FormMetaDataUpdate;
