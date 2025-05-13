export interface Curso {
  id?: number;
  nombre: string;
  codigo: string;
  creditos: number;
  cupoMaximo: number;
  inscritosActuales: number;
  contenido?: string;
  objetivos?: string;
  competencias?: string;
  programaId?: number;
  asignaturaId?: number;
  horarioId?: number;
  docentesIds?: number[];
  prerequisitosIds?: number[];
}