///**********************************************************************
// * Copyright(C) 2011 The Android Open Source Project
// *
// * License under the Apache License, Version 2.0(the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/license/LICENSE-2.0
// *
// * Unless required by applicable law or agreed in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
// * implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.bruce.study.demo.simple_image_loader_demo.disk_lru_cache;
//
//import java.io.Closeable;
//import java.io.IOException;
//
///**
// * A cache that uses a bounded amount of space on a filesystem. Each cache
// * entry has a string key and a fixed number of values. Each key must match
// * the regex <strong>[a-z0-9_-]{1,120}</strong>. Values are byte sequences,
// * accessible as streams or files. Each value must be between {@code 0} and
// * {@code Integer.MAX_VALUE} bytes in length.
// *
// * <p>The cache stores its data in a directory on the filesystem. This
// * directory must be exclusive to the cache; the cache may delete or overwrite
// * files from its directory. It is an error for multiple processes to use the
// * same cache directory at the same time.
// *
// * <p>This cache limits the number of bytes that it will store on the
// * filesystem. When the number of stored bytes exceeds the limit, the cache will
// * remove entries in the background until the limit is satisfied. The limit is
// * not strict: the cache may temporarily exceed it while waiting for files to be
// * deleted. The limit does not include filesystem overhead or the cache
// * journal so space-sensitive applications should set a conservative limit.
// *
// * <p>Clients call {@link #edit} to create or update the values of an entry. An
// * entry may have only one editor at one time; if a value is not available to be
// * edited then {@link #edit} will return null.
// * <ul>
// * <li>When an entry is being <strong>created</strong> it is necessary to
// * supply a full set of values; the empty value should be used as a
// * placeholder if necessary.
// * <li>When an entry is being <strong>edited</strong>, it is not necessary
// * to supply data for every value; values default to their previous
// * value.
// * </ul>
// * Every {@link #edit} call must be matched by a call to {@link Editor#commit}
// * or {@link Editor#abort}. Committing is atomic: a read observes the full set
// * of values as they were before or after the commit, but never a mix of values.
// *
// * <p>Clients call {@link #get} to read a snapshot of an entry. The read will
// * observe the value at the time that {@link #get} was called. Updates and
// * removals after the call do not impact ongoing reads.
// *
// * <p>This class is tolerant of some I/O errors. If files are missing from the
// * filesystem, the corresponding entries will be dropped from the cache. If
// * an error occurs while writing a cache value, the edit will fail silently.
// * Callers should handle other problems by catching {@code IOException} and
// * responding appropriately.
// */
////todo E:\test\github\simple_imageloader\src\com\jakewharton\disklrucache
//public class DiskLruCache implements Closeable{
//    @Override
//    public void close() throws IOException {
//
//    }
//}