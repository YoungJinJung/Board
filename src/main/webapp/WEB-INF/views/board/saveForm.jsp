<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form>
        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" class="form-control" id="title" placeholder="Enter title">
        </div>
        <div class="form-group">
            <label class="mr-sm-2" for="category">Category</label>
            <select class="custom-select mr-sm-2" id="category">
                <option selected>Choose Category</option>
                <option value="Tech">Tech</option>
                <option value="Sports">Sports</option>
                <option value="Entertainment">Entertainment</option>
            </select>
        </div>
        <div class="form-group">
            <textarea class="form-control summernote" rows="5" id="content"></textarea>
        </div>
    </form>
    <button id="btn-save" class="btn btn-primary">Save</button>

</div>

<script>
    $('.summernote').summernote({
        height: 300,                 // 에디터 높이
        minHeight: null,             // 최소 높이
        maxHeight: null,             // 최대 높이
        focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
        lang: "ko-KR",					// 한글 설정
        placeholder: '최대 2048자까지 쓸 수 있습니다'	//placeholder 설정
    });
</script>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>