package org.friedenberg.structures;

import net.datastructures.Edge;
import net.datastructures.Graph;
import net.datastructures.Vertex;
import net.datastructures.PositionalList;
import net.datastructures.Position;
import net.datastructures.Map;
import net.datastructures.LinkedPositionalList;
import net.datastructures.ProbeHashMap;

public class AdjacencyMapGraph<V,E>  implements Graph<V,E> {
    private boolean isDirected;
    private PositionalList<Vertex<V>> vertices = new LinkedPositionalList<>();
    private PositionalList<Edge<E>> edges = new LinkedPositionalList<>();

    public AdjacencyMapGraph(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public int numVertices() {
        return vertices.size();
    }

    public Iterable<Vertex<V>> vertices() {
        return vertices;
    }

    public int numEdges() {
        return edges.size();
    }

    public Iterable<Edge<E>> edges() {
        return edges;
    }

    public int outDegree(Vertex<V> v) {
        InnerVertex<V> vert = validate(v);
        return vert.getOutgoing().size();
    }

    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) {
        InnerVertex<V> vert = validate(v);
        return vert.outgoing.values();
    }

    public int inDegree(Vertex<V> v) {
        InnerVertex<V> vert = validate(v);
        return vert.getIncoming().size();
    }

    public Iterable<Edge<E>> incomingEdges(Vertex<V> v) {
        InnerVertex<V> vert = validate(v);
        return vert.incoming.values();
    }

    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) {
        InnerVertex<V> origin = validate(u);
        return origin.getOutgoing().get(v);
    }

    public Vertex<V>[] endVertices(Edge<E> e) {
        InnerEdge<E> edge = validate(e);
        return edge.getEndpoints();
    }

    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException {
        InnerEdge<E> edge = validate(e);
        Vertex<V>[] endpoints = edge.getEndpoints();
        if(endpoints[0] == v) {
            return endpoints[1];
        } else if (endpoints[1] == v) {
            return endpoints[0];
        } else {
            throw new IllegalArgumentException("v is not incident to this edge");
        }
    }

    public Vertex<V> insertVertex(V element) {
        InnerVertex<V> vertex = new InnerVertex<>(element, isDirected);
        vertex.setPosition(vertices.addLast(vertex));
        return vertex;
    }

    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws IllegalArgumentException {
        if(getEdge(u, v) == null) {
            InnerEdge<E> e = new InnerEdge<>(u, v, element);
            e.setPosition(edges.addLast(e));
            InnerVertex<V> origin = validate(u);
            InnerVertex<V> dest = validate(v);
            origin.getOutgoing().put(v, e);
            dest.getIncoming().put(u, e);
            return e;
        } else {
            throw new IllegalArgumentException("Edge from u to v already exists");
        }
    }

    public void removeVertex(Vertex<V> v) {
        InnerVertex<V> vert = validate(v);
        for (Edge<E> e : vert.getOutgoing().values()) {
            removeEdge(e);
        }
        for (Edge<E> e : vert.getIncoming().values()) {
            removeEdge(e);
        }
        vertices.remove(vert.getPosition());
    }

    public void removeEdge(Edge<E> e) throws IllegalArgumentException {
        InnerEdge<E> edge = validate(e);
        InnerVertex<V>[] endpoints = (InnerVertex<V>[]) edge.getEndpoints();
        endpoints[0].getOutgoing().remove(endpoints[1]);
        endpoints[1].getIncoming().remove(endpoints[0]);
        edges.remove(edge.getPosition());
        edge.setPosition(null);
    }

    @SuppressWarnings({"unchecked"})
    private InnerVertex<V> validate(Vertex<V> v) {
        if (!(v instanceof InnerVertex)) throw new IllegalArgumentException("Invalid vertex");
        InnerVertex<V> vert = (InnerVertex<V>) v;
        if (!vert.validate(this)) throw new IllegalArgumentException("Invalid vertex");
        return vert;
    }

    @SuppressWarnings({"unchecked"})
    private InnerEdge<E> validate(Edge<E> e) {
        if (!(e instanceof InnerEdge)) throw new IllegalArgumentException("Invalid edge");
        InnerEdge<E> edge = (InnerEdge<E>) e;
        if (!edge.validate(this)) throw new IllegalArgumentException("Invalid edge");
        return edge;
    }

    private class InnerVertex<V> implements Vertex<V> {
        private V element;
        private Position<Vertex<V>> position;
        private Map<Vertex<V>, Edge<E>> outgoing, incoming;

        public InnerVertex(V elem, boolean graphIsDirection) {
            element = elem;
            outgoing = new ProbeHashMap<>();
            if (graphIsDirection) {
                incoming = new ProbeHashMap<>();
            } else {
                incoming = outgoing;
            }
        }

        public V getElement() {
            return element;
        }

        public void setPosition(Position<Vertex<V>> position) {
            this.position = position;
        }

        public Position<Vertex<V>> getPosition() {
            return position;
        }

        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }

        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }

        public boolean validate(Graph<V,E> graph) {
            return (AdjacencyMapGraph.this == graph && position != null);
        }
    }

    private class InnerEdge<E> implements Edge<E> {
        private E element;
        private Position<Edge<E>> position;
        private Vertex<V>[] endpoints;

        @SuppressWarnings({"unchecked"})
        public InnerEdge(Vertex<V> u, Vertex<V> v, E elem) {
            element = elem;
            endpoints = (Vertex<V>[]) new Vertex[]{u,v};
        }

        @Override
        public E getElement() {
            return element;
        }

        public Position<Edge<E>> getPosition() {
            return position;
        }

        public void setPosition(Position<Edge<E>> position) {
            this.position = position;
        }

        public Vertex<V>[] getEndpoints() {
            return endpoints;
        }

        public boolean validate(Graph<V,E> graph) {
            return AdjacencyMapGraph.this == graph && position != null;
        }
    }
}
