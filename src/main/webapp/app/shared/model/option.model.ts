import { IFieldMetaData } from 'app/shared/model/field-meta-data.model';

export interface IOption {
  id?: number;
  value?: string | null;
  isDefault?: boolean | null;
  field?: IFieldMetaData | null;
}

export const defaultValue: Readonly<IOption> = {
  isDefault: false,
};
