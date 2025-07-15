export interface CompanyLocation {
  country: string;
  address: string;
}

export interface Company {
  title: string;
  email: string;
  phone: string;
  location: CompanyLocation;
}

export interface Person {
  index: number;
  name: string;
  isActive: boolean;
  registered: string;
  age: number;
  gender: string;
  eyeColor: string;
  favoriteFruit: string;
  company: Company;
  tags: string[];
}