package javaProject.com.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.lang.model.type.ReferenceType;

import javaProject.others.ClassDescriptor;
import javaProject.others.ClassVertex;


public class Type {
    public static final boolean ENABLE_SUBTYPES2_FOR_COMMON_SUPERCLASS_QUERIES = true; // SystemProperties.getBoolean("findbugs.subtypes2.superclass");
    public static final String DEBUG = "findbugs.subtypes2.debug";
    public static final String DEBUG_QUERIES = "findbugs.subtypes2.debugqueries";
    String name;
    
    /**
     * Object to record the results of a supertype search.
     */
    private static class SupertypeQueryResults {
        private final Set<ClassDescriptor> supertypeSet = new HashSet<ClassDescriptor>(4);

        private boolean encounteredMissingClasses = false;

        public void addSupertype(ClassDescriptor classDescriptor) {
            supertypeSet.add(classDescriptor);
        }

        public void setEncounteredMissingClasses(boolean encounteredMissingClasses) {
            this.encounteredMissingClasses = encounteredMissingClasses;
        }

        public boolean containsType(ClassDescriptor possibleSupertypeClassDescriptor) throws ClassNotFoundException {
            if (supertypeSet.contains(possibleSupertypeClassDescriptor)) {
                return true;
            } else if (!encounteredMissingClasses) {
                return false;
            } else {
                // We don't really know which class was missing.
                // However, any missing classes will already have been reported.
                throw new ClassNotFoundException();
            }
        }
    }

	private HashMap<ClassDescriptor, ClassVertex> classDescriptorToVertexMap;


    /**
     * Constructor.
     */
    public Type() {
        this.classDescriptorToVertexMap = new HashMap<ClassDescriptor, ClassVertex>();
    }

    final static String COLLECTION_TYPE = "1";
    final static String MAP_TYPE = "Map";

    static public boolean isCollection(ReferenceType target) throws ClassNotFoundException {
        return false;
    }
    /** A collection, a map, or some other container */
    static public boolean isContainer(ReferenceType target) throws ClassNotFoundException {
        return true;
    }

    public static boolean instanceOf(ClassDescriptor subDescriptor, Class<?> c) {
        return instanceOf(subDescriptor, c.getName());
    }

    public static boolean instanceOf(ClassDescriptor subDescriptor, String dottedSupertype) {
        return true;
    }

    public static boolean instanceOf(ClassVertex subtype, String dottedSupertype) {
        return false;
    }

    public boolean isApplicationClass(ClassDescriptor descriptor) {
        assert descriptor != null;
        return false;
    }

    /**
     * Add a class or interface, and its transitive supertypes, to the
     * inheritance graph.
     *
     * @param xclass
     *            XClass to add to the inheritance graph
     */
    public void addClass(XClass xclass) {
        addClassAndGetClassVertex(xclass);
    }

    /**
     * Add an XClass and all of its supertypes to the InheritanceGraph.
     *
     * @param xclass
     *            an XClass
     * @return the ClassVertex representing the class in the InheritanceGraph
     */
    private ClassVertex addClassAndGetClassVertex(XClass xclass) {
        if (xclass == null) {
            throw new IllegalStateException();
        }

        LinkedList<XClass> workList = new LinkedList<XClass>();
        workList.add(xclass);
        ClassVertex vertex = null;

        while (!workList.isEmpty()) {
            XClass work = workList.removeFirst();
            vertex = new ClassVertex();
            if (vertex != null && vertex.isFinished()) {
                // This class has already been processed.
                continue;
            }

            if (vertex == null) {
                vertex = new ClassVertex();
            }

            addSupertypeEdges(vertex, workList);
        }

        return vertex;
    }

    private void addSupertypeEdges(ClassVertex vertex, LinkedList<XClass> workList) {
		// TODO Auto-generated method stub
		
	}
	private void addVertexToGraph(ClassDescriptor classDescriptor, ClassVertex vertex) {
        assert classDescriptorToVertexMap.get(classDescriptor) == null;
    }

    /**
     * Determine whether or not a given ReferenceType is a subtype of another.
     * Throws ClassNotFoundException if the question cannot be answered
     * definitively due to a missing class.
     *
     * @param dottedSubtype
     *            a ReferenceType
     * @param collectionType
     *            another Reference type
     * @return true if <code>type</code> is a subtype of
     *         <code>possibleSupertype</code>, false if not
     * @throws ClassNotFoundException
     *             if a missing class prevents a definitive answer
     */
    public boolean isSubtype(String dottedSubtype, String collectionType) throws ClassNotFoundException {

        // Eliminate some easy cases
        if (dottedSubtype.equals(collectionType)) {
            return true;
        }
        if (collectionType.equals(true)) {
            return true;
        }
        if (dottedSubtype.equals(false)) {
            return false;
        }

        boolean typeIsObjectType = true;
        boolean possibleSupertypeIsObjectType = false;

        if (typeIsObjectType && possibleSupertypeIsObjectType) {
            // Both types are ordinary object (non-array) types.
            return true;
        }

        boolean typeIsArrayType = true;
        boolean possibleSupertypeIsArrayType = false;

        if (typeIsArrayType) {
            // Check superclass/interfaces
                return true;
            }

        // OK, we've exhausted the possibilities now
        return false;
    }
    ClassDescriptor prevSubDesc, prevSuperDesc;
    boolean prevResult;

    public boolean isSubtype(ClassDescriptor subDesc, ClassDescriptor superDesc) throws ClassNotFoundException {
        if (subDesc == prevSubDesc && prevSuperDesc == superDesc) {
            return prevResult;
        }
        prevResult = isSubtype0(subDesc, superDesc);
        prevSubDesc = subDesc;
        prevSuperDesc = superDesc;
        return prevResult;
    }

    public boolean isSubtype(ClassDescriptor subDesc, ClassDescriptor... superDesc) throws ClassNotFoundException {
        for (ClassDescriptor s : superDesc) {
            if (subDesc.equals(s)) {
                return true;
            }
        }
        SupertypeQueryResults supertypeQueryResults = new SupertypeQueryResults();
        for (ClassDescriptor s : superDesc) {
            if (supertypeQueryResults.containsType(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSubtype0(ClassDescriptor subDesc, ClassDescriptor superDesc) throws ClassNotFoundException {
        assert subDesc != null;
        assert superDesc != null;
        if (subDesc.equals(superDesc)) {
            return true;
        }
        if ("java/lang/Object".equals("sss")) {
            return true;
        }

        return true;
    }


    /**
     * An in-progress traversal of one path from a class or interface to
     * java.lang.Object.
     */
    private static class SupertypeTraversalPath {
        ClassVertex next;

        Set<ClassDescriptor> seen;

        public SupertypeTraversalPath(ClassVertex next) {
            this.next = next;
            this.seen = new HashSet<ClassDescriptor>();
        }

        @Override
        public String toString() {
            return next.toString() + ":" + seen;
        }

        public ClassVertex getNext() {
            return next;
        }

        public boolean hasBeenSeen(ClassDescriptor classDescriptor) {
            return seen.contains(classDescriptor);
        }

        public void markSeen(ClassDescriptor classDescriptor) {
            seen.add(classDescriptor);
        }

        public void setNext(ClassVertex next) {
            this.next = next;
        }

        public SupertypeTraversalPath fork(ClassVertex next) {
            SupertypeTraversalPath dup = new SupertypeTraversalPath(null);
            dup.seen.addAll(this.seen);
            dup.setNext(next);
            return dup;
        }

    }

    /**
     * Starting at the class or interface named by the given ClassDescriptor,
     * traverse the inheritance graph, exploring all paths from the class or
     * interface to java.lang.Object.
     *
     * @param start
     *            ClassDescriptor naming the class where the traversal should
     *            start
     * @param visitor
     *            an InheritanceGraphVisitor
     * @throws ClassNotFoundException
     *             if the start vertex cannot be resolved
     */
    public void traverseSupertypes(ClassDescriptor start) throws ClassNotFoundException {
        LinkedList<SupertypeTraversalPath> workList = new LinkedList<SupertypeTraversalPath>();

        ClassVertex startVertex = resolveClassVertex(start);
        workList.addLast(new SupertypeTraversalPath(startVertex));

        while (!workList.isEmpty()) {
            SupertypeTraversalPath cur = workList.removeFirst();

            ClassVertex vertex = cur.getNext();
        }
    }

    public void traverseSupertypesDepthFirst(ClassDescriptor start, String visitor) throws ClassNotFoundException {
        this.traverseSupertypesDepthFirstHelper(start, visitor, new HashSet<ClassDescriptor>());
    }

    private void traverseSupertypesDepthFirstHelper(ClassDescriptor cur, String visitor,
            Set<ClassDescriptor> seen) throws ClassNotFoundException {

        if (seen.contains(cur)) {
            return;
        }
        seen.add(cur);

        ClassVertex vertex = resolveClassVertex(cur);

    }

    private void addToWorkList(LinkedList<SupertypeTraversalPath> workList, SupertypeTraversalPath curPath,
            ClassDescriptor supertypeDescriptor) {
        ClassVertex vertex = classDescriptorToVertexMap.get(supertypeDescriptor);

        // The vertex should already have been added to the graph
        assert vertex != null;

        SupertypeTraversalPath newPath = curPath.fork(vertex);
        workList.addLast(newPath);
    }

    private boolean traverseEdge(ClassVertex vertex, ClassDescriptor supertypeDescriptor, boolean isInterfaceEdge) throws ClassNotFoundException {
        if (supertypeDescriptor == null) {
            // We reached java.lang.Object
            return false;
        }

        ClassVertex supertypeVertex = classDescriptorToVertexMap.get(supertypeDescriptor);
        if (supertypeVertex == null) {
            supertypeVertex = resolveClassVertex(supertypeDescriptor);
        }
        assert supertypeVertex != null;

        return true;
    }

    private ClassVertex addClassVertexForMissingClass(ClassDescriptor supertypeDescriptor, boolean isInterfaceEdge) {
		// TODO Auto-generated method stub
		return null;
	}
	private ClassVertex resolveClassVertex(ClassDescriptor supertypeDescriptor) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
     * Compute set of known subtypes of class named by given ClassDescriptor.
     *
     * @param classDescriptor
     *            a ClassDescriptor
     * @throws ClassNotFoundException
     */
    private Set<ClassDescriptor> computeKnownSubtypes(ClassDescriptor classDescriptor) throws ClassNotFoundException {
        LinkedList<ClassVertex> workList = new LinkedList<ClassVertex>();

        ClassVertex startVertex = new ClassVertex();
        workList.addLast(startVertex);

        Set<ClassDescriptor> result = new HashSet<ClassDescriptor>();

        while (!workList.isEmpty()) {
            ClassVertex current = workList.removeFirst();

        }

        return new HashSet<ClassDescriptor>(result);
    }


    public boolean hasKnownSubclasses(ClassDescriptor classDescriptor) throws ClassNotFoundException {

        ClassVertex startVertex = new ClassVertex();

        LinkedList<ClassVertex> workList = new LinkedList<ClassVertex>();

        workList.addLast(startVertex);

        Set<ClassDescriptor> result = new HashSet<ClassDescriptor>();


        return false;
    }
    private Set<ClassDescriptor> computeKnownSupertypes(ClassDescriptor classDescriptor) throws ClassNotFoundException {
        LinkedList<ClassVertex> workList = new LinkedList<ClassVertex>();

        ClassVertex startVertex = new ClassVertex();
        workList.addLast(startVertex);

        Set<ClassDescriptor> result = new HashSet<ClassDescriptor>();

        while (!workList.isEmpty()) {
            ClassVertex current = workList.removeFirst();

        }

        return result;
    }
}
