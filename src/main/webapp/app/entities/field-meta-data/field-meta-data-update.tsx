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
import { IFieldMetaData } from 'app/shared/model/field-meta-data.model';
import { DataType } from 'app/shared/model/enumerations/data-type.model';
import { getEntity, updateEntity, createEntity, reset } from './field-meta-data.reducer';

export const FieldMetaDataUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const formMetaData = useAppSelector(state => state.formMetaData.entities);
  const fieldMetaDataEntity = useAppSelector(state => state.fieldMetaData.entity);
  const loading = useAppSelector(state => state.fieldMetaData.loading);
  const updating = useAppSelector(state => state.fieldMetaData.updating);
  const updateSuccess = useAppSelector(state => state.fieldMetaData.updateSuccess);
  const dataTypeValues = Object.keys(DataType);
  const handleClose = () => {
    props.history.push('/field-meta-data');
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
    const entity = {
      ...fieldMetaDataEntity,
      ...values,
      form: formMetaData.find(it => it.id.toString() === values.form.toString()),
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
          type: 'TextBox',
          ...fieldMetaDataEntity,
          form: fieldMetaDataEntity?.form?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="marketingApp.fieldMetaData.home.createOrEditLabel" data-cy="FieldMetaDataCreateUpdateHeading">
            <Translate contentKey="marketingApp.fieldMetaData.home.createOrEditLabel">Create or edit a FieldMetaData</Translate>
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
                  id="field-meta-data-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('marketingApp.fieldMetaData.key')}
                id="field-meta-data-key"
                name="key"
                data-cy="key"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('marketingApp.fieldMetaData.type')}
                id="field-meta-data-type"
                name="type"
                data-cy="type"
                type="select"
              >
                {dataTypeValues.map(dataType => (
                  <option value={dataType} key={dataType}>
                    {translate('marketingApp.DataType.' + dataType)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('marketingApp.fieldMetaData.isMandatory')}
                id="field-meta-data-isMandatory"
                name="isMandatory"
                data-cy="isMandatory"
                check
                type="checkbox"
              />
              <ValidatedField
                id="field-meta-data-form"
                name="form"
                data-cy="form"
                label={translate('marketingApp.fieldMetaData.form')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/field-meta-data" replace color="info">
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

export default FieldMetaDataUpdate;
