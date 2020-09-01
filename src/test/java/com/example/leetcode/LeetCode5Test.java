package com.example.leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class LeetCode5Test {
    private LeetCode5 t = new LeetCode5();

    @Test
    void decompressRLElist() {
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
        String[] a = {"Pwsuo(71)","Prf(48)","Rgbu(49)","Zvzm(31)","Xxcl(25)","Bbcpth(42)","Padz(70)","Jmqqsj(19)","Uwy(26)","Jylbla(65)","Xioal(11)","Npbu(62)","Jpftyg(96)","Tal(46)","Hnc(100)","Yldu(85)","Alqw(45)","Wbcxi(34)","Kxjw(36)","Clplqf(8)","Fayxe(66)","Slfwyo(48)","Xbesji(70)","Pmbz(22)","Oip(2)","Fzoe(63)","Qync(79)","Utc(11)","Sqwejn(19)","Ngi(8)","Gsiiyo(60)","Bcs(73)","Icsvku(1)","Yzwm(92)","Vaakt(21)","Uvt(70)","Axaqkm(100)","Gyhh(84)","Gaoo(98)","Ghlj(35)","Umt(13)","Nfimij(52)","Zmeop(77)","Vje(29)","Rqa(47)","Upn(89)","Zhc(44)","Slh(66)","Orpqim(69)","Vxs(85)","Gql(19)","Sfjdjc(62)","Ccqunq(93)","Oyo(32)","Bvnkk(52)","Pxzfjg(45)","Kaaht(28)","Arrugl(57)","Vqnjg(50)","Dbufek(63)","Fshi(62)","Lvaaz(63)","Phlto(41)","Lnow(70)","Mqgga(31)","Adlue(82)","Zqiqe(27)","Mgs(46)","Zboes(56)","Dma(70)","Jnij(57)","Ghk(14)","Mrqlne(39)","Ljkzhs(35)","Rmlbnj(42)","Qszsny(93)","Aasipa(26)","Wzt(41)","Xuzubb(90)","Maeb(56)","Mlo(18)","Rttg(4)","Kmrev(31)","Kqjl(39)","Iggrg(47)","Mork(88)","Lwyfn(50)","Lcp(42)","Zpm(5)","Qlvglt(36)","Liyd(48)","Jxv(67)","Xaq(70)","Tkbn(81)","Rgd(85)","Ttj(28)","Ndc(62)","Bjfkzo(54)","Lqrmqh(50)","Vhdmab(41)"};
        String[] b = {"(Uvt,Rqa)","(Qync,Kqjl)","(Fayxe,Upn)","(Maeb,Xaq)","(Pmbz,Vje)","(Hnc,Dma)","(Pwsuo,Gyhh)","(Gyhh,Aasipa)","(Fzoe,Lcp)","(Mgs,Vhdmab)","(Qync,Rgd)","(Gql,Liyd)","(Gyhh,Tkbn)","(Arrugl,Adlue)","(Wbcxi,Slfwyo)","(Yzwm,Vqnjg)","(Lnow,Vhdmab)","(Lvaaz,Rttg)","(Nfimij,Iggrg)","(Vje,Lqrmqh)","(Jylbla,Ljkzhs)","(Jnij,Mlo)","(Adlue,Zqiqe)","(Qync,Rttg)","(Gsiiyo,Vxs)","(Xxcl,Fzoe)","(Dbufek,Xaq)","(Ccqunq,Qszsny)","(Zmeop,Mork)","(Qync,Ngi)","(Zboes,Rmlbnj)","(Yldu,Jxv)","(Padz,Gsiiyo)","(Oip,Utc)","(Tal,Pxzfjg)","(Adlue,Zpm)","(Bbcpth,Mork)","(Qync,Lvaaz)","(Pmbz,Qync)","(Alqw,Ngi)","(Bcs,Maeb)","(Rgbu,Zmeop)"};
        String[] strings = t.trulyMostPopular(a, b);
    }

    @Test
    void simplifyPath() {
        String s = t.simplifyPath("/a/./b/../../c/");
    }

    @Test
    void canMeasureWater() {
        boolean b = t.canMeasureWater(2, 6, 5);
    }

    @Test
    void countOfAtomsHelper() {
        String h20 = t.countOfAtoms("((N42)24(OB40Li30CHe3O48LiNN26)33(C12Li48N30H13HBe31)21(BHN30Li26BCBe47N40)15(H5)16)14");
    }

    @Test
    void shortestSubarray() {
        int i = t.shortestSubarray(new int[]{1}, 1);
    }

    @Test
    void minTransfers() {
        int i = t.minTransfers(new int[][]{{1,8,1},{1,13,21},{2,8,10},{3,9,20},{4,10,61},{5,11,61},{6,12,59},{7,13,60}});
    }

    @Test
    void boldWords() {
        String aabcd = t.boldWords(new String[]{"ab", "bc"}, "aabcd");
    }

    @Test
    void repeatedSubstringPattern() {
        boolean abab = t.repeatedSubstringPattern("abaababaab");
    }

    @Test
    void isPalindrome() {
        boolean palindrome = t.isPalindrome3(ListNode.create(new int[]{1, 2,2,1}));
    }

    @Test
    void addTwoNumbers() {
        ListNode node = t.addTwoNumbers(ListNode.create(new int[]{2, 4, 3}), ListNode.create(new int[]{5, 6, 4}));
    }

    @Test
    void nextLargerNodes() {
        int[] ints = t.nextLargerNodes(ListNode.create(new int[]{2, 7, 4, 3, 5}));
    }

    @Test
    void findSubsequences() {
        List<List<Integer>> subsequences = t.findSubsequences(new int[]{1,9,1,1,1});
    }

    @Test
    void letterCombinations() {
        HashMap<String, List<String>> map = new HashMap<>();
        List<String> s = new ArrayList<>();
        s.add("d");
        s.add("e");
        s.add("f");
        map.put("a", s);
        List<String> a = map.get("a");
        for (int i = 0; i < 3; i++) {
            s.remove(s.size()-1);
        }
    }

    @Test
    void subsetsWithDup() {
        List<List<Integer>> list = t.subsetsWithDup(new int[]{1, 2, 2});
    }

    @Test
    void getPermutation2() {
        String permutation2 = t.getPermutation2(3, 3);
    }

    @Test
    void canVisitAllRooms() {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Arrays.asList(1,3));
        rooms.add(Arrays.asList(3,0,1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList(0));
        boolean b = t.canVisitAllRooms(rooms);
    }

    @Test
    void restoreIpAddresses() {
        List<String> strings = t.restoreIpAddresses("25525511135");
    }
}