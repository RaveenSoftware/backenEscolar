export interface Docente {
  id?: number;
  nombre: string;
  telefono: string;
  correoPersonal: string;
  fechaNacimiento: Date;
  numeroDocumento: string;
  estado: boolean;
  especialidad: string;
  codigoInstitucional: string;
  correoInstitucional: string;
  facultadId?: number;
  tipoDocumentoId?: number;
  tipoGeneroId?: number;
  cursosIds?: number[];
  disponibilidadHorariaIds?: number[];
}