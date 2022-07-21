import { IFieldMetaData } from 'app/shared/model/field-meta-data.model';

export interface IOption {
  id?: number;
  valueStr?: string;
  title?: string;
  isDefault?: boolean;
  field?: IFieldMetaData | null;
}

export const defaultValue: Readonly<IOption> = {
  isDefault: false,
};
