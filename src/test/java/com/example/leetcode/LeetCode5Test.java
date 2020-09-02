package com.example.leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LeetCode5Test {
    private LeetCode5 t = new LeetCode5();

    @Test
    void decompressRLElist() {
        int b = "a".compareTo("b");
    }

    @Test
    void recoverFromPreorder() {
        TreeNode treeNode = t.recoverFromPreorder("1-2--3---4-5--6---7");
    }

    @Test
    void recoverFromPreorderII() {
        TreeNode treeNode = t.recoverFromPreorderII("1-401--349---90--88");
    }

    @Test
    void getPermutation() {
        String permutation = t.getPermutation(3, 3);
    }

    @Test
    void trulyMostPopular() {
        String[] a = {"Pwsuo(71)", "Prf(48)", "Rgbu(49)", "Zvzm(31)", "Xxcl(25)", "Bbcpth(42)", "Padz(70)", "Jmqqsj(19)", "Uwy(26)", "Jylbla(65)", "Xioal(11)", "Npbu(62)", "Jpftyg(96)", "Tal(46)", "Hnc(100)", "Yldu(85)", "Alqw(45)", "Wbcxi(34)", "Kxjw(36)", "Clplqf(8)", "Fayxe(66)", "Slfwyo(48)", "Xbesji(70)", "Pmbz(22)", "Oip(2)", "Fzoe(63)", "Qync(79)", "Utc(11)", "Sqwejn(19)", "Ngi(8)", "Gsiiyo(60)", "Bcs(73)", "Icsvku(1)", "Yzwm(92)", "Vaakt(21)", "Uvt(70)", "Axaqkm(100)", "Gyhh(84)", "Gaoo(98)", "Ghlj(35)", "Umt(13)", "Nfimij(52)", "Zmeop(77)", "Vje(29)", "Rqa(47)", "Upn(89)", "Zhc(44)", "Slh(66)", "Orpqim(69)", "Vxs(85)", "Gql(19)", "Sfjdjc(62)", "Ccqunq(93)", "Oyo(32)", "Bvnkk(52)", "Pxzfjg(45)", "Kaaht(28)", "Arrugl(57)", "Vqnjg(50)", "Dbufek(63)", "Fshi(62)", "Lvaaz(63)", "Phlto(41)", "Lnow(70)", "Mqgga(31)", "Adlue(82)", "Zqiqe(27)", "Mgs(46)", "Zboes(56)", "Dma(70)", "Jnij(57)", "Ghk(14)", "Mrqlne(39)", "Ljkzhs(35)", "Rmlbnj(42)", "Qszsny(93)", "Aasipa(26)", "Wzt(41)", "Xuzubb(90)", "Maeb(56)", "Mlo(18)", "Rttg(4)", "Kmrev(31)", "Kqjl(39)", "Iggrg(47)", "Mork(88)", "Lwyfn(50)", "Lcp(42)", "Zpm(5)", "Qlvglt(36)", "Liyd(48)", "Jxv(67)", "Xaq(70)", "Tkbn(81)", "Rgd(85)", "Ttj(28)", "Ndc(62)", "Bjfkzo(54)", "Lqrmqh(50)", "Vhdmab(41)"};
        String[] b = {"(Uvt,Rqa)", "(Qync,Kqjl)", "(Fayxe,Upn)", "(Maeb,Xaq)", "(Pmbz,Vje)", "(Hnc,Dma)", "(Pwsuo,Gyhh)", "(Gyhh,Aasipa)", "(Fzoe,Lcp)", "(Mgs,Vhdmab)", "(Qync,Rgd)", "(Gql,Liyd)", "(Gyhh,Tkbn)", "(Arrugl,Adlue)", "(Wbcxi,Slfwyo)", "(Yzwm,Vqnjg)", "(Lnow,Vhdmab)", "(Lvaaz,Rttg)", "(Nfimij,Iggrg)", "(Vje,Lqrmqh)", "(Jylbla,Ljkzhs)", "(Jnij,Mlo)", "(Adlue,Zqiqe)", "(Qync,Rttg)", "(Gsiiyo,Vxs)", "(Xxcl,Fzoe)", "(Dbufek,Xaq)", "(Ccqunq,Qszsny)", "(Zmeop,Mork)", "(Qync,Ngi)", "(Zboes,Rmlbnj)", "(Yldu,Jxv)", "(Padz,Gsiiyo)", "(Oip,Utc)", "(Tal,Pxzfjg)", "(Adlue,Zpm)", "(Bbcpth,Mork)", "(Qync,Lvaaz)", "(Pmbz,Qync)", "(Alqw,Ngi)", "(Bcs,Maeb)", "(Rgbu,Zmeop)"};
        String[] strings = t.trulyMostPopular(a, b);
    }

    @Test
    void islandPerimeter() {
        int i = t.islandPerimeter(new int[][]{{0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}});
    }

    @Test
    void LFUCache() {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);
        lfu.put(2, 2);
        lfu.get(1);
        lfu.put(3, 3);
        lfu.get(2);
        lfu.get(3);
        lfu.put(4, 4);
        lfu.get(1);
        lfu.get(3);
        lfu.get(4);
    }

    @Test
    void threeConsecutiveOdds() {
        boolean b = t.threeConsecutiveOdds(new int[]{2, 6, 4, 1});
    }

    @Test
    void minOperations() {
        int i = t.minOperations(6);
    }


    @Test
    void levelOrder() {
        List<List<Integer>> lists = t.levelOrder(TreeNode.create(new Integer[]{3, 9, 20, null, null, 15, 7}));
    }

    @Test
    void reverseNode() {
        ListNode node = t.reverseNode(ListNode.create(new int[]{4, 1, 8, 4, 5}));
    }

    @Test
    void getIntersectionNode() {
        ListNode a = ListNode.create(new int[]{4, 1});
        ListNode c = ListNode.create(new int[]{8, 4, 5});
        ListNode d = ListNode.create(new int[]{5, 0, 1});
        a.next.next = c;
        d.next.next.next = c;
        ListNode intersectionNode = t.getIntersectionNode(a, d);
    }

    @Test
    void sortedListToBST() {
        TreeNode treeNode = t.sortedListToBST(ListNode.create(new int[]{0, 1, 2, 3, 4, 5}));
    }

    @Test
    void thousandSeparator() {
        String s = t.thousandSeparator(0);
    }

    @Test
    void findSmallestSetOfVertices() {
    }

    @Test
    void testFindSmallestSetOfVertices() {
        List<List<Integer>> in = new ArrayList<>();
        in.add(Arrays.asList(0, 1));
        in.add(Arrays.asList(0, 2));
        in.add(Arrays.asList(2, 5));
        in.add(Arrays.asList(3, 4));
        in.add(Arrays.asList(4, 2));
        List<Integer> smallestSetOfVertices = t.findSmallestSetOfVertices(6, in);
    }

    @Test
    void findItinerary() {
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("MUC","LHR"));
        tickets.add(Arrays.asList("JFK","MUC"));
        tickets.add(Arrays.asList("SFO","SJC"));
        tickets.add(Arrays.asList("LHR","SFO"));
        List<String> itinerary = t.findItinerary(tickets);
    }

    @Test
    void findKthNumber() {
        int kthNumber = t.findKthNumber(2, 2);
    }

//    @Test
//    void reorganizeString() {
//        String string = t.reorganizeString("bfrbs");
//    }
}
