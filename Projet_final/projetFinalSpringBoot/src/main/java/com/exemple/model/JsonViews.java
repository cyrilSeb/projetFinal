package com.exemple.model;

public class JsonViews {

	public static class Common {
	};

	public static class Competence extends Common {
	};

	public static class CompetenceWithFormateur extends Competence {
	};

	public static class CompetenceWithMatiere extends Competence {
	};

	public static class OrdiWithStagiaire extends Common {
	};

	public static class ProjoWithCursus extends Common {
	};

	public static class Cursus extends Common {
	};

	public static class CursusWithGestionnaire extends Cursus {
	};

	public static class CursusWithReferent extends Cursus {
	};

	public static class CursusWithModules extends Cursus {
	};

	public static class CursusWithProjo extends Cursus {
	};

	public static class CursusWithStagiaire extends Cursus {
	};

	public static class CursusWithSalle extends Cursus {
	};

	public static class MatiereWithPrerequis extends Common {
	};

	public static class Module extends Common {
	};

	public static class ModuleWithMatiere extends Module {
	};

	public static class ModuleWithFormateur extends Module {
	};

	public static class ModuleWithCursus extends Module {
	};

	public static class SalleWithCursus extends Common {
	};

	public static class Authentification extends Common {
	};

	public static class User extends Common {
	};

	public static class UserWithCompetence extends User {
	};

	public static class UserWithModule extends User {
	};

	public static class UserWithCursus extends User {
	};

	public static class UserWithOrdi extends User {
	};
}
