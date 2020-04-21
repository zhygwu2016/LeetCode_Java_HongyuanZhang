package somethingElse.java;

import java.util.List;

/*
算法加强 Trie
Router longest prefix matching
A network router takes incoming packet from network, and forwards it to the
corresponding outgoing port. It uses a routing table to make the forward decision.
Each routing table entries contains a prefix(you can think it as a bit array)
and a port number, and the it takes the entry that has the longest prefix match with
the packet (also a bit array) to get the target port from.

Example:
000         3
10          4
1           5
no-match    8

incoming packet        Port
00010                   3
1001                    4
111                     5
001010                  8

You are given a list of entries:
class Entry {
    boolean[] prefix;
    int port;
}

implement the two methods:
void build(List<Entry> entries);
int route(boolean[] packet);
 */
class Entry {
    boolean[] prefix;
    int port;
}

class TrieNode{
    public TrieNode[] children;
    public int portNum;

    public TrieNode(){
        children = new TrieNode[2];
        portNum = -1;
    }
}

public class Router_longest_prefix_matching {
    private TrieNode root = new TrieNode();

    public void build(List<Entry> entries) {
        for (Entry entry : entries) {
            boolean[] prefix = entry.prefix;
            TrieNode cur = root;
            for (boolean element : prefix) {
                int idx = element ? 1 : 0;
                if (cur.children[idx] == null) {
                    cur.children[idx] = new TrieNode();
                }
                cur = cur.children[idx];
            }
            cur.portNum = entry.port;
        }
    }

    public int route(boolean[] packet) {
        if (packet == null || packet.length == 0) {
            throw new IllegalArgumentException();
        }

        TrieNode cur = root;
        int portRes = 8;

        for (boolean element : packet) {
            int idx = element ? 1 : 0;
            if (cur.children[idx] == null) {
                return portRes;
            } else {
                cur = cur.children[idx];
                portRes = cur.portNum;
            }
        }

        return cur.portNum;
    }
}
