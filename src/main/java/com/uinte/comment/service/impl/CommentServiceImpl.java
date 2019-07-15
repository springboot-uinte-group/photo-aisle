package com.uinte.comment.service.impl;


import org.springframework.stereotype.Service;

import com.uinte.comment.service.ICommentService;
import com.uinte.common.service.BaseService;
import com.uinte.model.TComment;

/**
 * 
 * @author hzc 2017年3月1日
 *
 */
@Service(value = "commentService")
public class CommentServiceImpl extends BaseService<TComment> implements ICommentService {


}
