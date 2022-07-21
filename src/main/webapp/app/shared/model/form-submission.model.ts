import { IFieldSubmission } from 'app/shared/model/field-submission.model';
import { IFormMetaData } from 'app/shared/model/form-meta-data.model';

export interface IFormSubmission {
  id?: number;
  sessionidentifier?: string | null;
  fieldSubmissions?: IFieldSubmission[] | null;
  formKey?: IFormMetaData | null;
}

export const defaultValue: Readonly<IFormSubmission> = {};
