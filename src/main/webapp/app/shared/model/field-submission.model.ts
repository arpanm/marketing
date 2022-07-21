import { IFieldMetaData } from 'app/shared/model/field-meta-data.model';
import { IFormSubmission } from 'app/shared/model/form-submission.model';

export interface IFieldSubmission {
  id?: number;
  value?: string | null;
  field?: IFieldMetaData | null;
  field?: IFormSubmission | null;
}

export const defaultValue: Readonly<IFieldSubmission> = {};
