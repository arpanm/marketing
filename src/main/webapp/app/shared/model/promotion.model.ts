import dayjs from 'dayjs';
import { IFormMetaData } from 'app/shared/model/form-meta-data.model';

export interface IPromotion {
  id?: number;
  name?: string;
  urlName?: string;
  desktopImageUrl?: string;
  tabletImageUrl?: string;
  mobileImageUrl?: string;
  title?: string;
  description?: string;
  tnc?: string | null;
  tncLink?: string | null;
  landingUrl?: string;
  position?: number;
  isEnabled?: boolean;
  startDate?: string;
  endDate?: string;
  createdBy?: string;
  createdDate?: string;
  updatedBy?: string;
  updatedDate?: string;
  form?: IFormMetaData | null;
}

export const defaultValue: Readonly<IPromotion> = {
  isEnabled: false,
};
