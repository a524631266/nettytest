package com.zhangllhome.io.nio.zerocopy;


/**
 * 传统ＩＯ
 * 1. 硬盘copy　　DMA direct memory access 直接内存copy,不使用cpu　，就是直接从硬盘中ｃｏｐｙ到（内核）内存中
 * 2. 系内核内存copy　　（内核）内存通过ＣＰＵ copy到用户态的内存中
 * 3. 用户态内存copy=> sokect　　修改完的内存再回到socket 内核中
 * 4. 网络内存 => 物理网络协议栈中（　DMA direct）　　内核态数据再返回给协议栈（网络程序使用的内存空间）
 *
 * ｍmap 并不是真正的零copy 第二步　是有用户态切换的
 * 1. 硬盘copy　　DMA direct memory access 直接内存copy,不使用cpu　，就是直接从硬盘中ｃｏｐｙ到（内核）内存中
 * 2. 系内核内存＝＞sokect内核 　　（内核）内存通过文件映射到缓存区　　直接到网络内核内存中
 * 3网络内存 => 物理网络协议栈中（　DMA direct）　　内核态数据再返回给协议栈（网络程序使用的内存空间）
 *
 * sendFile　第二步　是没有用户态切换，但是仍然会经过cpu　ｃｏｐｙ　从系统内核到网络内核中
 *  * 1. 硬盘copy　　DMA direct memory access 直接内存copy,不使用cpu　，就是直接从硬盘中ｃｏｐｙ到（内核）内存中
 *  * 2. 系内核内存＝＞sokect内核 　　（内核）内存通过文件映射到缓存区　　直接到网络内核内存中
 *  * 3网络内存 => 物理网络协议栈中（　DMA direct）　　内核态数据再返回给协议栈（网络程序使用的内存空间）
 *
 *  零copy
 *  是指　第二步中没有cpu copy的
 *  在ｌｉｎｕｘ　2.4版本中仍然有不过只ｃｏｐｙ相对较少的信息　ｌｅｎｇｔｈ　或　ｏｆｆｓｅｔ
 */

public class TestZero {


}
