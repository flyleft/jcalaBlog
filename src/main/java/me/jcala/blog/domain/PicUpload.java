package me.jcala.blog.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Administrator on 2016/9/24.
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PicUpload {
    private int success;//0 表示上传失败，1 表示上传成功
    private String message;//图片上传提示信息,包括上传成功或上传失败及错误信息等
    private String url;//图片上传成功后返回的地址

}
