import { IOption } from 'app/shared/model/option.model';
import { IFieldSubmission } from 'app/shared/model/field-submission.model';
import { IFormMetaData } from 'app/shared/model/form-meta-data.model';
import { DataType } from 'app/shared/model/enumerations/data-type.model';

export interface IFieldMetaData {
  id?: number;
  key?: string;
  type?: DataType;
  isMandatory?: boolean;
  options?: IOption[] | null;
  fieldSubmissions?: IFieldSubmission[] | null;
  form?: IFormMetaData | null;
}

export const defaultValue: Readonly<IFieldMetaData> = {
  isMandatory: false,
};
