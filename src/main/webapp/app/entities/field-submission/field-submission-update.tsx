import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IFieldMetaData } from 'app/shared/model/field-meta-data.model';
import { getEntities as getFieldMetaData } from 'app/entities/field-meta-data/field-meta-data.reducer';
import { IFormSubmission } from 'app/shared/model/form-submission.model';
import { getEntities as getFormSubmissions } from 'app/entities/form-submission/form-submission.reducer';
import { IFieldSubmission } from 'app/shared/model/field-submission.model';
import { getEntity, updateEntity, createEntity, reset } from './field-submission.reducer';

export const FieldSubmissionUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const fieldMetaData = useAppSelector(state => state.fieldMetaData.entities);
  const formSubmissions = useAppSelector(state => state.formSubmission.entities);
  const fieldSubmissionEntity = useAppSelector(state => state.fieldSubmission.entity);
  const loading = useAppSelector(state => state.fieldSubmission.loading);
  const updating = useAppSelector(state => state.fieldSubmission.updating);
  const updateSuccess = useAppSelector(state => state.fieldSubmission.updateSuccess);
  const handleClose = () => {
    props.history.push('/field-submission');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getFieldMetaData({}));
    dispatch(getFormSubmissions({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...fieldSubmissionEntity,
      ...values,
      fieldKey: fieldMetaData.find(it => it.id.toString() === values.fieldKey.toString()),
      formSub: formSubmissions.find(it => it.id.toString() === values.formSub.toString()),
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
          ...fieldSubmissionEntity,
          fieldKey: fieldSubmissionEntity?.fieldKey?.id,
          formSub: fieldSubmissionEntity?.formSub?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="marketingApp.fieldSubmission.home.createOrEditLabel" data-cy="FieldSubmissionCreateUpdateHeading">
            <Translate contentKey="marketingApp.fieldSubmission.home.createOrEditLabel">Create or edit a FieldSubmission</Translate>
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
                  id="field-submission-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('marketingApp.fieldSubmission.valueStr')}
                id="field-submission-valueStr"
                name="valueStr"
                data-cy="valueStr"
                type="text"
              />
              <ValidatedField
                id="field-submission-fieldKey"
                name="fieldKey"
                data-cy="fieldKey"
                label={translate('marketingApp.fieldSubmission.fieldKey')}
                type="select"
              >
                <option value="" key="0" />
                {fieldMetaData
                  ? fieldMetaData.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="field-submission-formSub"
                name="formSub"
                data-cy="formSub"
                label={translate('marketingApp.fieldSubmission.formSub')}
                type="select"
              >
                <option value="" key="0" />
                {formSubmissions
                  ? formSubmissions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/field-submission" replace color="info">
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

export default FieldSubmissionUpdate;
