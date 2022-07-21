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
import { IFormSubmission } from 'app/shared/model/form-submission.model';
import { getEntity, updateEntity, createEntity, reset } from './form-submission.reducer';

export const FormSubmissionUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const formMetaData = useAppSelector(state => state.formMetaData.entities);
  const formSubmissionEntity = useAppSelector(state => state.formSubmission.entity);
  const loading = useAppSelector(state => state.formSubmission.loading);
  const updating = useAppSelector(state => state.formSubmission.updating);
  const updateSuccess = useAppSelector(state => state.formSubmission.updateSuccess);
  const handleClose = () => {
    props.history.push('/form-submission');
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
      ...formSubmissionEntity,
      ...values,
      formKey: formMetaData.find(it => it.id.toString() === values.formKey.toString()),
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
          ...formSubmissionEntity,
          formKey: formSubmissionEntity?.formKey?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="marketingApp.formSubmission.home.createOrEditLabel" data-cy="FormSubmissionCreateUpdateHeading">
            <Translate contentKey="marketingApp.formSubmission.home.createOrEditLabel">Create or edit a FormSubmission</Translate>
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
                  id="form-submission-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('marketingApp.formSubmission.sessionidentifier')}
                id="form-submission-sessionidentifier"
                name="sessionidentifier"
                data-cy="sessionidentifier"
                type="text"
              />
              <ValidatedField
                id="form-submission-formKey"
                name="formKey"
                data-cy="formKey"
                label={translate('marketingApp.formSubmission.formKey')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/form-submission" replace color="info">
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

export default FormSubmissionUpdate;
