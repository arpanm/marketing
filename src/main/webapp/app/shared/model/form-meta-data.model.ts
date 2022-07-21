import { IFieldMetaData } from 'app/shared/model/field-meta-data.model';
import { IFormSubmission } from 'app/shared/model/form-submission.model';
import { IPromotion } from 'app/shared/model/promotion.model';

export interface IFormMetaData {
  id?: number;
  name?: string;
  title?: string;
  isActive?: boolean;
  multiSubmissionAllowed?: boolean;
  fieldMetaData?: IFieldMetaData[] | null;
  formSubmissions?: IFormSubmission[] | null;
  promotion?: IPromotion | null;
}

export const defaultValue: Readonly<IFormMetaData> = {
  isActive: false,
  multiSubmissionAllowed: false,
};
